package com.example.data.repositories

import com.example.data.api.RemoteServiceHelper
import com.example.data.mappers.PassDataEntityMapper
import com.example.domain.abstractions.Repository
import com.example.domain.entities.PassEntity
import io.reactivex.Flowable
import io.reactivex.Single

class RepositoryImpl(
    private val remoteServiceHelper: RemoteServiceHelper,
    private val passDataEntityMapper: PassDataEntityMapper
) : Repository {

    override fun getPasses(latitude: String, longitude: String): Single<List<PassEntity>> {
        return remoteServiceHelper.getPasses(latitude, longitude).toFlowable()
            .flatMap { Flowable.fromIterable(it.passData)}
            .map{passDataEntityMapper.mapFrom(it)}
            .toList()
    }
}