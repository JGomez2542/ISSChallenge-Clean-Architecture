package com.example.jasongomez.isschallenge.presentation.passes

import android.annotation.SuppressLint
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import android.util.Log
import com.example.jasongomez.isschallenge.domain.common.CURRENT_LOCATION
import com.example.jasongomez.isschallenge.domain.usecases.GetPermissions
import com.example.jasongomez.isschallenge.domain.usecases.GetLocation
import com.example.jasongomez.isschallenge.domain.usecases.GetPasses
import com.example.jasongomez.isschallenge.presentation.mappers.PassEntityPassMapper
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PassesPresenter(
    private val passesContractView: PassesContract.View,
    private val getLocation: GetLocation,
    private val getPasses: GetPasses,
    private val passEntityPassMapper: PassEntityPassMapper,
    private val getPermissions: GetPermissions
) : PassesContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    @SuppressLint("CheckResult")
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    override fun onStart() {
        Log.d(this::class.java.simpleName, "Listener onStart")
        //compositeDisposable.add(
        getPermissions.observable()
            .subscribeOn(Schedulers.io())
            .doOnNext { Log.d(this::class.java.simpleName, "Listener PassesPresenter onNext $it")}
            .doOnSubscribe {  Log.d(this::class.java.simpleName, "Listener PassesPresenter onSubscribe") }
            .doOnError {  Log.d(this::class.java.simpleName, "Listener GetPermissions onError ${it.message}") }
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
            })
        //)
    }

    @SuppressLint("CheckResult")
    override fun getPasses() {
        Log.d(this::class.java.simpleName, "Listener Presenter")
        getLocation.observable()
            .observeOn(Schedulers.io())
            .flatMap { getPasses.observable(mapOf(CURRENT_LOCATION to it)) }
            .flatMap { Observable.fromIterable(it) }
            .map { passEntityPassMapper.mapFrom(it) }
            .toList()
            .doOnSuccess {  Log.d(this::class.java.simpleName, "GetLocation ${it.size}") }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ passesContractView.displayPasses(it) }, { it.printStackTrace() })
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    override fun onStop() {
        Log.d(this::class.java.simpleName, "Listener onStop")
        //compositeDisposable.clear()
        getLocation.removeListener()
    }
}