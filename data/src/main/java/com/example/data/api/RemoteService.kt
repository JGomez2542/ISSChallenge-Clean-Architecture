package com.example.data.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteService {

    @GET("iss-pass.json")
    fun getPasses(@Query("lat") latitude: String, @Query("lon") longitude: String): Single<ISSResult>
}