package com.example.jasongomez.isschallenge.domain.usecases

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import com.example.jasongomez.isschallenge.domain.common.AsyncTransformer
import com.example.jasongomez.isschallenge.domain.common.PERMISSIONS_REQUEST_CODE
import com.example.jasongomez.isschallenge.presentation.base.BaseActivity
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class GetPermissions @Inject constructor(private val activity: Activity, transformer: AsyncTransformer<Boolean>): UseCase<Boolean>(transformer) {

    private val hasPermissionsObservable = BehaviorSubject.create<Boolean>()

    override fun createObservable(data: Map<String, Any>?): Observable<Boolean> {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_FINE_LOCATION)) {
                (activity as BaseActivity<*>).showMessage("Your location is needed in order to get the international space station pass times")
            } else {
                ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    PERMISSIONS_REQUEST_CODE
                )
            }
        } else {
            hasPermissionsObservable.onNext(true)
        }
        return hasPermissionsObservable
    }
}