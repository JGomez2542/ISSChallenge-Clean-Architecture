package com.example.presentation.passes

import androidx.lifecycle.LifecycleObserver
import com.example.presentation.base.BasePresenter
import com.example.presentation.base.BaseView
import com.example.presentation.entities.Pass

interface PassesContract {
    interface Presenter: BasePresenter, LifecycleObserver {
        fun getPasses()
    }
    interface View: BaseView {
        fun displayPasses(passes: List<Pass>)
        fun checkPermissions():Boolean
    }
}