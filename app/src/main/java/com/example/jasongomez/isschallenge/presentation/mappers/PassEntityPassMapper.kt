package com.example.jasongomez.isschallenge.presentation.mappers

import com.example.jasongomez.isschallenge.domain.common.Mapper
import com.example.jasongomez.isschallenge.domain.entities.PassEntity
import com.example.jasongomez.isschallenge.presentation.entities.Pass
import javax.inject.Inject

class PassEntityPassMapper @Inject constructor() : Mapper<PassEntity, Pass>() {

    override fun mapFrom(from: PassEntity): Pass = Pass(riseTime = from.riseTime, duration = from.duration)
}