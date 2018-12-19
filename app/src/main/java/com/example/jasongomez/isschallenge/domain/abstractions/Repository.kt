package com.example.jasongomez.isschallenge.domain.abstractions

import com.example.jasongomez.isschallenge.domain.entities.PassEntity
import io.reactivex.Single

interface Repository {
    fun getPasses(latitude: String, longitude: String): Single<List<PassEntity>>
}