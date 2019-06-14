package com.example.data.mappers

import com.example.data.entities.PassData
import com.example.core.common.Mapper
import com.example.domain.entities.PassEntity
import javax.inject.Inject

class PassDataEntityMapper @Inject constructor() : Mapper<PassData, PassEntity>() {

    override fun mapFrom(from: PassData): PassEntity =
        PassEntity(riseTime = from.risetime, duration = from.duration)
}