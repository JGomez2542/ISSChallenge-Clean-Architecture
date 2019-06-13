package com.example.jasongomez.isschallenge.data.mappers

import com.example.jasongomez.isschallenge.data.entities.RequestData
import com.example.domain.common.Mapper
import com.example.domain.entities.RequestEntity

class RequestDataEntityMapper : com.example.domain.common.Mapper<RequestData, com.example.domain.entities.RequestEntity>() {

    override fun mapFrom(from: RequestData): com.example.domain.entities.RequestEntity {
        return com.example.domain.entities.RequestEntity(
            latitude = from.latitude, longitude = from.longitude,
            altitude = from.altitude, passes = from.passes, datetime = from.datetime
        )
    }
}