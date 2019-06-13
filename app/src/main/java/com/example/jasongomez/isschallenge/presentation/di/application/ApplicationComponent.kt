package com.example.jasongomez.isschallenge.presentation.di.application

import com.example.jasongomez.isschallenge.data.api.RemoteServiceHelper
import com.example.jasongomez.isschallenge.data.mappers.PassDataEntityMapper
import com.example.jasongomez.isschallenge.presentation.di.activity.ActivityComponent
import com.example.jasongomez.isschallenge.presentation.di.activity.ActivityModule
import dagger.Component

@ApplicationScope
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun newActivityComponent(activityModule: ActivityModule): ActivityComponent

    fun getRemoteServiceHelper(): RemoteServiceHelper

    fun getPassDataEntityMapper(): PassDataEntityMapper

}