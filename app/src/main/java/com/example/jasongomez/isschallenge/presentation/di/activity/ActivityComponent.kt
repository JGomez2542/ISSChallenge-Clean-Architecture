package com.example.jasongomez.isschallenge.presentation.di.activity

import android.location.Location
import com.example.jasongomez.isschallenge.domain.common.AsyncTransformer
import com.example.jasongomez.isschallenge.domain.common.SyncTransformer
import com.example.jasongomez.isschallenge.domain.entities.PassEntity
import com.example.jasongomez.isschallenge.domain.usecases.GetLocation
import com.example.jasongomez.isschallenge.domain.usecases.GetPasses
import com.example.jasongomez.isschallenge.presentation.passes.MainActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)

    fun getAsyncTransformer(): AsyncTransformer<List<PassEntity>>

    fun getSyncTransformer(): SyncTransformer<Location>

    fun getPassesUseCase(): GetPasses

    fun getLocationUseCase(): GetLocation
}