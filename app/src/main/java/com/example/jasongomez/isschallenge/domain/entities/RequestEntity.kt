package com.example.jasongomez.isschallenge.domain.entities

data class RequestEntity(
    val latitude: String, val longitude: String, val altitude: String,
    val passes: String, val datetime: String
)