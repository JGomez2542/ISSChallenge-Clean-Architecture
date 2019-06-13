package com.example.domain.usecases

import com.example.domain.abstractions.Repository
import com.example.domain.common.AsyncTransformer
import com.example.domain.common.CURRENT_LOCATION
import com.example.domain.entities.LocationEntity
import com.example.domain.entities.PassEntity
import io.reactivex.Observable

class GetPasses(
    transformer: AsyncTransformer<List<PassEntity>>,
    private val repository: Repository
) : UseCase<List<PassEntity>>(transformer) {

    override fun createObservable(data: Map<String, Any>?): Observable<List<PassEntity>> {
        val currentLocation = data?.get(CURRENT_LOCATION) as LocationEntity
        return repository.getPasses(currentLocation.latitude.toString(), currentLocation.longitude.toString())
            .toObservable()
    }
}