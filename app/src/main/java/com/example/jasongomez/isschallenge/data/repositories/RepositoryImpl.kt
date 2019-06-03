package com.example.jasongomez.isschallenge.data.repositories

import com.example.jasongomez.isschallenge.data.api.RemoteServiceHelper
import com.example.jasongomez.isschallenge.data.mappers.PassDataEntityMapper
import com.example.jasongomez.isschallenge.domain.abstractions.Repository
import com.example.jasongomez.isschallenge.domain.entities.PassEntity
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