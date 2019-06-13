package com.example.domain.usecases

import io.reactivex.Observable

/**
 * Also known as interactors, use cases encapsulate a single, very specific task that can be performed.
 */
abstract class UseCase<T>(private val transformer: com.example.domain.common.Transformer<T>) {

    abstract fun createObservable(data: Map<String, Any>? = null): Observable<T>

    fun observable(withData: Map<String, Any>? = null): Observable<T> = createObservable(withData).compose(transformer)
}