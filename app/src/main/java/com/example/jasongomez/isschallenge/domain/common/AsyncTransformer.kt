package com.example.jasongomez.isschallenge.domain.common

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AsyncTransformer<T> @Inject constructor() : Transformer<T>() {

    override fun apply(upstream: Observable<T>): ObservableSource<T> =
        upstream//upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}