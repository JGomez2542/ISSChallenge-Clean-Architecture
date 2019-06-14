package com.example.presentation.passes

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.example.AppController
import com.example.core.common.PERMISSIONS_REQUEST_CODE
import com.example.presentation.R
import com.example.presentation.adapters.PassAdapter
import com.example.presentation.base.BaseActivity
import com.example.presentation.di.activity.ActivityModule
import com.example.presentation.entities.Pass
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity<PassesContract.Presenter>(),
    PassesContract.View {

    @Inject
    lateinit var passesPresenter: PassesContract.Presenter

    override fun init() {
        (application as AppController).applicationComponent.newActivityComponent(
            ActivityModule(
                this
            )
        ).inject(this)
        lifecycle.addObserver(passesPresenter)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun getPresenter(): PassesContract.Presenter = passesPresenter

    override fun checkPermissions(): Boolean {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                showMessage("Your location is needed in order to get the international space station pass times")
            } else {
                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    PERMISSIONS_REQUEST_CODE
                )
            }
            return false
        } else {
            return true
        }
    }

    override fun displayPasses(passes: List<Pass>) {
        rvPasses.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = PassAdapter(passes)
            addItemDecoration(DividerItemDecoration(context, (layoutManager as LinearLayoutManager).orientation))
        }
    }

    override fun showMessage(message: String) {
        AlertDialog.Builder(this)
            .setTitle("Message")
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.cancel()
                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    PERMISSIONS_REQUEST_CODE
                )
            }
            .show()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSIONS_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    passesPresenter.getPasses()
                }
            }
        }
    }
}
