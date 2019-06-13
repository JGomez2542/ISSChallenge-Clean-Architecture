package com.example.devices.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import com.example.devices.mappers.LocationLocationEntityMapper
import com.example.domain.abstractions.LocationProvider
import com.example.domain.entities.LocationEntity
import io.reactivex.*

class CurrentLocation(context: Context) : LocationProvider {

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
    override fun getLocation(): Observable<LocationEntity> {
        Log.d(this::class.java.simpleName, "Listener Added")
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            1000,
            100f,
            locationListener
        )
        else locationManager.requestLocationUpdates(
            LocationManager.NETWORK_PROVIDER,
            1000,
            100f,
            locationListener
        )
        return Observable.just(
            LocationLocationEntityMapper().mapFrom(
                locationManager.getLastKnownLocation(
                    if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
                        LocationManager.GPS_PROVIDER else LocationManager.NETWORK_PROVIDER
                )
            )
        )
    }

    @SuppressLint("MissingPermission")
    override fun removeListener() {
        Log.d(this::class.java.simpleName, "Listener Removed")
        locationManager.removeUpdates(locationListener)
    }
}