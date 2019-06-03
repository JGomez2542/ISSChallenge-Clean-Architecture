package com.example.jasongomez.isschallenge.domain.abstractions

import android.location.Location
import io.reactivex.Observable

interface LocationProvider {
    fun getLocation(): Observable<Location>
    fun removeListener()
}