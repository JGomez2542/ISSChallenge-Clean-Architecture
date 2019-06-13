package com.example.domain.abstractions

import com.example.domain.entities.PassEntity
import io.reactivex.Single

interface Repository {
    fun getPasses(latitude: String, longitude: String): Single<List<PassEntity>>
}