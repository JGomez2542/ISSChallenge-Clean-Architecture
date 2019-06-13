package com.example.jasongomez.isschallenge.data.mappers

import com.example.jasongomez.isschallenge.data.entities.PassData
import com.example.domain.common.Mapper
import com.example.domain.entities.PassEntity
import javax.inject.Inject

class PassDataEntityMapper @Inject constructor() : com.example.domain.common.Mapper<PassData, com.example.domain.entities.PassEntity>() {

    override fun mapFrom(from: PassData): com.example.domain.entities.PassEntity =
        com.example.domain.entities.PassEntity(riseTime = from.risetime, duration = from.duration)
}