package com.example.domain.abstractions

import com.example.domain.entities.LocationEntity
import io.reactivex.Observable

interface LocationProvider {
    fun getLocation(): Observable<LocationEntity>
    fun removeListener()
}