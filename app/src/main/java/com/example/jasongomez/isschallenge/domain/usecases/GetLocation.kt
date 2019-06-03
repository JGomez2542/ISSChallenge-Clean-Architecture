package com.example.jasongomez.isschallenge.domain.usecases

import android.location.Location
import android.util.Log
import com.example.jasongomez.isschallenge.domain.abstractions.LocationProvider
import com.example.jasongomez.isschallenge.domain.common.SyncTransformer
import io.reactivex.Observable
import javax.inject.Inject

class GetLocation @Inject constructor(transformer: SyncTransformer<Location>, private val locationProvider: LocationProvider) :
    UseCase<Location>(transformer) {

    override fun createObservable(data: Map<String, Any>?): Observable<Location> {
        Log.d(this::class.java.simpleName, "Observable added")
        return locationProvider.getLocation()
    }

    fun removeListener() {
        locationProvider.removeListener()
    }
}