package com.example.jasongomez.isschallenge.data.mappers

import com.example.jasongomez.isschallenge.data.entities.PassData
import com.example.jasongomez.isschallenge.domain.common.Mapper
import com.example.jasongomez.isschallenge.domain.entities.PassEntity
import javax.inject.Inject

class PassDataEntityMapper @Inject constructor() : Mapper<PassData, PassEntity>() {

    override fun mapFrom(from: PassData): PassEntity =
        PassEntity(riseTime = from.risetime, duration = from.duration)
}