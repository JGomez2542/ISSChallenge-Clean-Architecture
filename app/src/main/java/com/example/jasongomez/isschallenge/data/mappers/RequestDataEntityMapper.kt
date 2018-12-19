package com.example.jasongomez.isschallenge.data.mappers

import com.example.jasongomez.isschallenge.data.entities.RequestData
import com.example.jasongomez.isschallenge.domain.common.Mapper
import com.example.jasongomez.isschallenge.domain.entities.RequestEntity

class RequestDataEntityMapper : Mapper<RequestData, RequestEntity>() {

    override fun mapFrom(from: RequestData): RequestEntity {
        return RequestEntity(
            latitude = from.latitude, longitude = from.longitude,
            altitude = from.altitude, passes = from.passes, datetime = from.datetime
        )
    }
}