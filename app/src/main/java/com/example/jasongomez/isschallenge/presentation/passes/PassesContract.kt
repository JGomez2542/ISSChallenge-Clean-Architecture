package com.example.jasongomez.isschallenge.presentation.passes

import com.example.jasongomez.isschallenge.presentation.base.BasePresenter
import com.example.jasongomez.isschallenge.presentation.base.BaseView
import com.example.jasongomez.isschallenge.presentation.entities.Pass

interface PassesContract {
    interface Presenter: BasePresenter {
        fun getPasses()
    }
    interface View: BaseView {
        fun displayPasses(passes: List<Pass>)
    }
}