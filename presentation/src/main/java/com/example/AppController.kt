package com.example

import android.app.Application
import com.example.presentation.di.application.ApplicationComponent
import com.example.presentation.di.application.ApplicationModule
import com.example.presentation.di.application.DaggerApplicationComponent

class AppController : Application() {

    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}