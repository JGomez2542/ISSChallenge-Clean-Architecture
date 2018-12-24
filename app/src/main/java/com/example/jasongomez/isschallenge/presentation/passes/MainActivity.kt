package com.example.jasongomez.isschallenge.presentation.passes

import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.example.jasongomez.isschallenge.AppController
import com.example.jasongomez.isschallenge.R
import com.example.jasongomez.isschallenge.domain.common.PERMISSIONS_REQUEST_CODE
import com.example.jasongomez.isschallenge.presentation.adapters.PassAdapter
import com.example.jasongomez.isschallenge.presentation.base.BaseActivity
import com.example.jasongomez.isschallenge.presentation.di.activity.ActivityModule
import com.example.jasongomez.isschallenge.presentation.entities.Pass
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity<PassesContract.Presenter>(), PassesContract.View {

    @Inject
    lateinit var passesPresenter: PassesContract.Presenter

    override fun init() {
        (application as AppController).applicationComponent.newActivityComponent(ActivityModule(this)).inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun getPresenter(): PassesContract.Presenter = passesPresenter

    override fun displayPasses(passes: List<Pass>) {
        rvPasses.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = PassAdapter(passes)
            addItemDecoration(DividerItemDecoration(context, (layoutManager as LinearLayoutManager).orientation))
        }
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
