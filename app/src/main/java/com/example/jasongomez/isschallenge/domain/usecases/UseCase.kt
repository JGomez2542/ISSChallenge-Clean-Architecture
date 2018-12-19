package com.example.jasongomez.isschallenge.domain.usecases

import com.example.jasongomez.isschallenge.domain.common.Transformer
import io.reactivex.Observable

/**
 * Also known as interactors, use cases encapsulate a single, very specific task that can be performed.
 */
abstract class UseCase<T>(private val transformer: Transformer<T>) {

    abstract fun createObservable(data: Map<String, Any>? = null): Observable<T>

    fun observable(withData: Map<String, Any>? = null): Observable<T> = createObservable(withData).compose(transformer)
}