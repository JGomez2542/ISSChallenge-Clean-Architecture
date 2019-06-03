package com.example.jasongomez.isschallenge.presentation.di.activity

import android.content.Context
import android.support.v7.app.AppCompatActivity
import com.example.jasongomez.isschallenge.domain.CurrentLocation
import com.example.jasongomez.isschallenge.domain.abstractions.LocationProvider
import com.example.jasongomez.isschallenge.domain.usecases.GetPermissions
import com.example.jasongomez.isschallenge.domain.usecases.GetLocation
import com.example.jasongomez.isschallenge.domain.usecases.GetPasses
import com.example.jasongomez.isschallenge.presentation.mappers.PassEntityPassMapper
import com.example.jasongomez.isschallenge.presentation.passes.PassesContract
import com.example.jasongomez.isschallenge.presentation.passes.PassesPresenter
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val passView: PassesContract.View) {

    @Provides
    @ActivityScope
    fun providesPassesPresenter(
        getLocation: GetLocation, getPasses: GetPasses,
        passEntityPassMapper: PassEntityPassMapper, getPermissions: GetPermissions
    ): PassesContract.Presenter = PassesPresenter(
        passView,
        getLocation = getLocation,
        getPasses = getPasses,
        passEntityPassMapper = passEntityPassMapper,
        getPermissions = getPermissions
    )

    @Provides
    @ActivityScope
    fun providesActivity(): AppCompatActivity = (passView as AppCompatActivity)

    @Provides
    @ActivityScope
    fun providesLocationProvider(context: Context): LocationProvider = CurrentLocation(context)
}