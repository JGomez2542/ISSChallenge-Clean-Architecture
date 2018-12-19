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

class CurrentLocation(private val context: Context) :
    LocationProvider {

    @SuppressLint("MissingPermission")
    override fun getLocation(): Observable<Location> = Observable.create(object : ObservableOnSubscribe<Location> {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        override fun subscribe(emitter: ObservableEmitter<Location>) {
            val locationListener = object : LocationListener {
                override fun onLocationChanged(location: Location?) {
                    location?.let {
                        emitter.onNext(it)
                        emitter.onComplete()
                    }
                }

                override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
                }

                override fun onProviderEnabled(provider: String?) {
                }

                override fun onProviderDisabled(provider: String?) {
                }
            }
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

            emitter.setCancellable {
                locationManager.removeUpdates(locationListener)
            }
        }
    })

}