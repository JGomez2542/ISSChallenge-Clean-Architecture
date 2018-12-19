package com.example.jasongomez.isschallenge.domain

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import com.example.jasongomez.isschallenge.domain.abstractions.LocationProvider
import com.example.jasongomez.isschallenge.domain.common.MIN_LOCATION_UPDATES_DISTANCE
import io.reactivex.*
import io.reactivex.subjects.BehaviorSubject

class CurrentLocation(private val context: Context) : LocationProvider {

    private val locationManager: LocationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    private val subject: BehaviorSubject<Location> = BehaviorSubject.create<Location>()
    private val locationListener = object : LocationListener {
        override fun onLocationChanged(location: Location?) {
            location?.let {
                subject.onNext(it)
                subject.onComplete()
            }
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        }

        override fun onProviderEnabled(provider: String?) {
        }

        override fun onProviderDisabled(provider: String?) {
        }
    }

    @SuppressLint("MissingPermission")
    override fun getLocation(): Observable<Location> {
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            0,
            MIN_LOCATION_UPDATES_DISTANCE,
            locationListener
        )
        else locationManager.requestLocationUpdates(
            LocationManager.NETWORK_PROVIDER,
            0,
            MIN_LOCATION_UPDATES_DISTANCE,
            locationListener
        )
        return subject
    }
}