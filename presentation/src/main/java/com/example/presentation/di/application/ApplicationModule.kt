package com.example.presentation.di.application

import android.content.Context
import com.example.data.api.RemoteServiceHelper
import com.example.data.mappers.PassDataEntityMapper
import com.example.data.repositories.RepositoryImpl
import com.example.domain.abstractions.Repository
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val context: Context) {

    @Provides
    @ApplicationScope
    fun providesContext() = context

    @Provides
    @ApplicationScope
    fun providesRepository(
        remoteServiceHelper: RemoteServiceHelper,
        passDataEntityMapper: PassDataEntityMapper
    ): Repository = RepositoryImpl(remoteServiceHelper, passDataEntityMapper)
}