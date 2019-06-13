package com.example.data.mappers

import com.example.data.entities.RequestData
import com.example.domain.common.Mapper
import com.example.domain.entities.RequestEntity

class RequestDataEntityMapper : Mapper<RequestData, RequestEntity>() {

    override fun mapFrom(from: RequestData): RequestEntity {
        return RequestEntity(
            latitude = from.latitude, longitude = from.longitude,
            altitude = from.altitude, passes = from.passes, datetime = from.datetime
        )
    }
}