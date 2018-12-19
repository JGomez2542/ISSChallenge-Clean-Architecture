package com.example.jasongomez.isschallenge.domain.usecases

import android.location.Location
import com.example.jasongomez.isschallenge.domain.abstractions.Repository
import com.example.jasongomez.isschallenge.domain.common.AsyncTransformer
import com.example.jasongomez.isschallenge.domain.common.CURRENT_LOCATION
import com.example.jasongomez.isschallenge.domain.entities.PassEntity
import io.reactivex.Observable
import javax.inject.Inject

class GetPasses @Inject constructor(
    transformer: AsyncTransformer<List<PassEntity>>,
    private val repository: Repository
) : UseCase<List<PassEntity>>(transformer) {

    override fun createObservable(data: Map<String, Any>?): Observable<List<PassEntity>> {
        val currentLocation = data?.get(CURRENT_LOCATION) as Location
        return repository.getPasses(currentLocation.latitude.toString(), currentLocation.longitude.toString())
            .toObservable()
    }
}