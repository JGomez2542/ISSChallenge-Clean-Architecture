package com.example.jasongomez.isschallenge.presentation.di.activity

import com.example.jasongomez.isschallenge.presentation.passes.MainActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)
}