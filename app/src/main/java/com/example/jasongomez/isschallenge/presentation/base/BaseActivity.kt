package com.example.jasongomez.isschallenge.presentation.base

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity<T : BasePresenter> : AppCompatActivity(), BaseView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun onStart() {
        super.onStart()
        getPresenter().onStart()
    }

    override fun onStop() {
        super.onStop()
        getPresenter().onStop()
    }

    override fun showMessage(message: String) {
        showDialog(message, "Message")
    }

    override fun showError(message: String) {
        showDialog(message, "Error")
    }

    private fun showDialog(message: String, title: String) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ -> dialog.cancel() }
            .show()
    }

    abstract fun init()
    abstract fun getPresenter(): T
}