package com.example.devices.permissions

import android.Manifest
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.example.domain.abstractions.PermissionsManager
import com.example.domain.common.PERMISSIONS_REQUEST_CODE

class CheckPermissions(private val activity: AppCompatActivity): PermissionsManager {

    override fun arePermissionsGranted(): Boolean {
        if (ContextCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    activity,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                //(activity as BaseActivity<*>).showMessage("Your location is needed in order to get the international space station pass times")
            } else {
                ActivityCompat.requestPermissions(
                    activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    PERMISSIONS_REQUEST_CODE
                )
            }
            return false
        } else {
            return true
        }
    }
}