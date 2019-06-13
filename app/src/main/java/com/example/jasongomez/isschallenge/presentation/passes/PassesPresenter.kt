package com.example.jasongomez.isschallenge.presentation.passes

import android.annotation.SuppressLint
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import android.util.Log
import com.example.domain.abstractions.PermissionsManager
import com.example.domain.common.CURRENT_LOCATION
import com.example.domain.usecases.GetPermissions
import com.example.domain.usecases.GetLocation
import com.example.domain.usecases.GetPasses
import com.example.jasongomez.isschallenge.presentation.mappers.PassEntityPassMapper
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PassesPresenter(
    private val passesContractView: PassesContract.View,
    private val getLocation: GetLocation,
    private val getPasses: GetPasses,
    private val passEntityPassMapper: PassEntityPassMapper,
    private val permissions: PermissionsManager
) : PassesContract.Presenter {

    @SuppressLint("CheckResult")
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    override fun onStart() {
        if (permissions.arePermissionsGranted()) {
            getPasses()
        }
        /*getPermissions.observable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Boolean> {
                override fun onSubscribe(d: Disposable) {
                    Log.d(this::class.java.simpleName, "Listener GetPermissions onComplete")
                }

                override fun onComplete() {
                    Log.d(this::class.java.simpleName, "Listener GetPermissions onComplete")
                }

                override fun onNext(t: Boolean) {
                    if (t) {
                        Log.d(this::class.java.simpleName, "Listener GetPermissions onNext")
                        getPasses()
                    } else {
                        Log.d(this::class.java.simpleName, "Listener GetPermissions onNext")
                    }
                }

                override fun onError(e: Throwable) {
                    Log.d(this::class.java.simpleName, "Listener GetPermissions onError")
                    e.printStackTrace()
                }
            })*/
    }

    @SuppressLint("CheckResult")
    override fun getPasses() {
        getLocation.observable()
            .observeOn(Schedulers.io())
            .flatMap { getPasses.observable(mapOf(CURRENT_LOCATION to it)) }
            .flatMap { Observable.fromIterable(it) }
            .map { passEntityPassMapper.mapFrom(it) }
            .toList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ passesContractView.displayPasses(it) }, { it.printStackTrace() })
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    override fun onStop() {
        getLocation.removeListener()
    }
}