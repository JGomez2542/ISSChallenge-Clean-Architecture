package com.example.presentation.passes

import android.annotation.SuppressLint
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import com.example.domain.abstractions.PermissionsManager
import com.example.domain.common.CURRENT_LOCATION
import com.example.domain.usecases.GetLocation
import com.example.domain.usecases.GetPasses
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PassesPresenter(
    private val passesContractView: com.example.presentation.passes.PassesContract.View,
    private val getLocation: GetLocation,
    private val getPasses: GetPasses,
    private val passEntityPassMapper: com.example.presentation.mappers.PassEntityPassMapper,
    private val permissions: PermissionsManager
) : com.example.presentation.passes.PassesContract.Presenter {

    @SuppressLint("CheckResult")
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    override fun onStart() {
        if (permissions.arePermissionsGranted()) {
            getPasses()
        }
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