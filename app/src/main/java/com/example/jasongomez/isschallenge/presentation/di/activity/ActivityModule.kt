package com.example.jasongomez.isschallenge.presentation.di.activity

import android.content.Context
import android.support.v7.app.AppCompatActivity
import com.example.devices.location.CurrentLocation
import com.example.devices.permissions.CheckPermissions
import com.example.domain.abstractions.LocationProvider
import com.example.domain.abstractions.PermissionsManager
import com.example.domain.abstractions.Repository
import com.example.domain.common.AsyncTransformer
import com.example.domain.common.SyncTransformer
import com.example.domain.entities.LocationEntity
import com.example.domain.entities.PassEntity
import com.example.domain.usecases.GetLocation
import com.example.domain.usecases.GetPasses
import com.example.domain.usecases.GetPermissions
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
        passEntityPassMapper: PassEntityPassMapper, permissionsManager: PermissionsManager
    ): PassesContract.Presenter = PassesPresenter(
        passView,
        getLocation = getLocation,
        getPasses = getPasses,
        passEntityPassMapper = passEntityPassMapper,
        permissions = permissionsManager
    )

    @Provides
    @ActivityScope
    fun providesPermissionsManager(activity: AppCompatActivity): PermissionsManager = CheckPermissions(activity)

    @Provides
    @ActivityScope
    fun providesActivity(): AppCompatActivity = (passView as AppCompatActivity)

    @Provides
    @ActivityScope
    fun providesLocationProvider(context: Context): LocationProvider = CurrentLocation(context)

    @Provides
    @ActivityScope
    fun providesGetPermissionsUseCase(permissionsManager: PermissionsManager, transformer: AsyncTransformer<Boolean>) =
        GetPermissions(permissionsManager, transformer)

    @Provides
    @ActivityScope
    fun providesGetLocationUseCase(transformer: SyncTransformer<LocationEntity>, locationProvider: LocationProvider) =
        GetLocation(transformer, locationProvider)

    @Provides
    @ActivityScope
    fun providesGetPassesUseCase(transformer: AsyncTransformer<List<PassEntity>>, repository: Repository) =
        GetPasses(transformer, repository)

    @Provides
    @ActivityScope
    fun providesAsyncTransformer(): AsyncTransformer<List<PassEntity>> = AsyncTransformer()

    @Provides
    @ActivityScope
    fun providesSyncTransformer(): SyncTransformer<LocationEntity> = SyncTransformer()
}