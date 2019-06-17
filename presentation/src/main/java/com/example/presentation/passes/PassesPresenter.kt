package com.example.presentation.passes

import android.annotation.SuppressLint
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.example.core.common.CURRENT_LOCATION
import com.example.domain.usecases.GetLocation
import com.example.domain.usecases.GetPasses
import com.example.presentation.mappers.PassEntityPassMapper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

class PassesPresenter(
    private val passesContractView: PassesContract.View,
    private val getLocation: GetLocation,
    private val getPasses: GetPasses,
    private val passEntityPassMapper: PassEntityPassMapper
) : PassesContract.Presenter {

    @SuppressLint("CheckResult")
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    override fun onStart() {
        if (passesContractView.checkPermissions()) {
            getPasses()
        }
    }

    @SuppressLint("CheckResult")
    override fun getPasses() {
        getLocation.observable()
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