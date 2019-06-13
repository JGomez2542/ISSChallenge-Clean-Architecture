package com.example.domain.common

import io.reactivex.Observable
import io.reactivex.ObservableSource

class AsyncTransformer<T> : Transformer<T>() {

    override fun apply(upstream: Observable<T>): ObservableSource<T> =
        upstream//upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}