package com.example.jasongomez.isschallenge

import android.app.Application
import com.example.jasongomez.isschallenge.presentation.di.application.ApplicationComponent
import com.example.jasongomez.isschallenge.presentation.di.application.ApplicationModule
import com.example.jasongomez.isschallenge.presentation.di.application.DaggerApplicationComponent

class AppController : Application() {

    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}