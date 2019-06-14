package com.example.devices.mappers

import android.location.Location
import com.example.core.common.Mapper
import com.example.domain.entities.LocationEntity

class LocationLocationEntityMapper : Mapper<Location, LocationEntity>() {

    override fun mapFrom(from: Location): LocationEntity = LocationEntity(latitude = from.latitude, longitude = from.longitude)
}