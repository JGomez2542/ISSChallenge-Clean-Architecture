package com.example.jasongomez.isschallenge.domain.common

import io.reactivex.Observable
import io.reactivex.ObservableSource
import javax.inject.Inject

class SyncTransformer<T> @Inject constructor() : Transformer<T>() {
    override fun apply(upstream: Observable<T>): ObservableSource<T> = upstream

}