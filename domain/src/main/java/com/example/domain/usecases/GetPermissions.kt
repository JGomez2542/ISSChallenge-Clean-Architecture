package com.example.domain.usecases


import com.example.domain.abstractions.PermissionsManager
import com.example.domain.common.AsyncTransformer
import io.reactivex.Observable

class GetPermissions (
    private val permissionsManager: PermissionsManager,
    transformer: AsyncTransformer<Boolean>
) : UseCase<Boolean>(transformer) {

    override fun createObservable(data: Map<String, Any>?): Observable<Boolean> =
        Observable.fromCallable { arePermissionsGranted() }

    private fun arePermissionsGranted() = permissionsManager.arePermissionsGranted()
}