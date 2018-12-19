package com.example.jasongomez.isschallenge.presentation.di.application

import android.content.Context
import com.example.jasongomez.isschallenge.data.api.RemoteServiceHelper
import com.example.jasongomez.isschallenge.data.mappers.PassDataEntityMapper
import com.example.jasongomez.isschallenge.data.repositories.RepositoryImpl
import com.example.jasongomez.isschallenge.domain.abstractions.Repository
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