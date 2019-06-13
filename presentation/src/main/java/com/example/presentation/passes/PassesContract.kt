package com.example.presentation.passes

import android.arch.lifecycle.LifecycleObserver
import com.example.presentation.base.BasePresenter
import com.example.presentation.base.BaseView
import com.example.presentation.entities.Pass

interface PassesContract {
    interface Presenter: com.example.presentation.base.BasePresenter, LifecycleObserver {
        fun getPasses()
    }
    interface View: com.example.presentation.base.BaseView {
        fun displayPasses(passes: List<com.example.presentation.entities.Pass>)
    }
}