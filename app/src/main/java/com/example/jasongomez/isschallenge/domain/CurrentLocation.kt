package com.example.jasongomez.isschallenge.domain

import android.annotation.SuppressLint
import android.arch.lifecycle.LifecycleObserver
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import com.example.jasongomez.isschallenge.domain.abstractions.LocationProvider
import com.example.jasongomez.isschallenge.domain.common.MIN_LOCATION_UPDATES_DISTANCE
import io.reactivex.*

class CurrentLocation(context: Context) : LocationProvider, LifecycleObserver {

    private val locationManager: LocationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    private val locationListener = object : LocationListener {
        override fun onLocationChanged(location: Location?) {
            location?.let {
                Log.d(this::class.java.simpleName, "Listener onLocationChanged")
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
        Log.d(this::class.java.simpleName, "Listener Added")
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            1000,
            MIN_LOCATION_UPDATES_DISTANCE,
            locationListener
        )
        else locationManager.requestLocationUpdates(
            LocationManager.NETWORK_PROVIDER,
            1000,
            MIN_LOCATION_UPDATES_DISTANCE,
            locationListener
        )
        return Observable.just(
            locationManager.getLastKnownLocation(
                if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
                    LocationManager.GPS_PROVIDER else LocationManager.NETWORK_PROVIDER
            )
        )
    }

    override fun removeListener() {
        Log.d(this::class.java.simpleName, "Listener Removed")
        locationManager.removeUpdates(locationListener)
    }
}