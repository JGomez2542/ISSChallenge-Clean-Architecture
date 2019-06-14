package com.example.core.common

import io.reactivex.Observable
import io.reactivex.ObservableSource

class SyncTransformer<T> : Transformer<T>() {
    override fun apply(upstream: Observable<T>): ObservableSource<T> = upstream

}