package com.example.jasongomez.isschallenge.presentation.passes

import android.annotation.SuppressLint
import android.location.Location
import com.example.jasongomez.isschallenge.domain.common.CURRENT_LOCATION
import com.example.jasongomez.isschallenge.domain.usecases.GetPermissions
import com.example.jasongomez.isschallenge.domain.usecases.GetLocation
import com.example.jasongomez.isschallenge.domain.usecases.GetPasses
import com.example.jasongomez.isschallenge.presentation.mappers.PassEntityPassMapper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PassesPresenter(private val passesContractView: PassesContract.View, private val getLocation: GetLocation, private val getPasses: GetPasses,
                      private val passEntityPassMapper: PassEntityPassMapper, private val getPermissions: GetPermissions
):PassesContract.Presenter {

    @SuppressLint("CheckResult")
    override fun onStart() {
        getPermissions.createObservable()
            .subscribe({if (it == true) getPasses()}, {it.printStackTrace()})
    }

    @SuppressLint("CheckResult")
    override fun getPasses() {
        getLocation.observable()
            .observeOn(Schedulers.io())
            .flatMap { t: Location -> getPasses.observable(mapOf(CURRENT_LOCATION to t)) }
            .flatMap { it -> Observable.fromIterable(it) }
            .map { passEntityPassMapper.mapFrom(it) }
            .toList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ passesContractView.displayPasses(it) }, { it.printStackTrace() })
    }

    override fun onStop() {
    }
}