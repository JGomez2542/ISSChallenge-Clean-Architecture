package com.example.domain.usecases

import com.example.domain.abstractions.LocationProvider
import com.example.domain.common.SyncTransformer
import com.example.domain.entities.LocationEntity
import io.reactivex.Observable

class GetLocation(transformer: SyncTransformer<LocationEntity>, private val locationProvider: LocationProvider) :
    UseCase<LocationEntity>(transformer) {

    override fun createObservable(data: Map<String, Any>?): Observable<LocationEntity> = locationProvider.getLocation()

    fun removeListener() {
        locationProvider.removeListener()
    }
}