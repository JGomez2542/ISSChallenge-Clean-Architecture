package com.example.presentation.di.application

import com.example.data.api.RemoteServiceHelper
import com.example.data.mappers.PassDataEntityMapper
import com.example.presentation.di.activity.ActivityComponent
import com.example.presentation.di.activity.ActivityModule
import dagger.Component

@ApplicationScope
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun newActivityComponent(activityModule: ActivityModule): ActivityComponent

    fun getRemoteServiceHelper(): RemoteServiceHelper

    fun getPassDataEntityMapper(): PassDataEntityMapper

}