package com.example.presentation.mappers

import com.example.domain.common.Mapper
import com.example.domain.entities.PassEntity
import com.example.presentation.entities.Pass
import javax.inject.Inject

class PassEntityPassMapper @Inject constructor() : Mapper<PassEntity, Pass>() {

    override fun mapFrom(from: PassEntity): Pass =
        Pass(riseTime = from.riseTime, duration = from.duration)
}