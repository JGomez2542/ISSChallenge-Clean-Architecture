package com.example.jasongomez.isschallenge.data.api

import com.example.jasongomez.isschallenge.data.entities.PassData
import com.example.jasongomez.isschallenge.data.entities.RequestData
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ISSResult(
    @SerializedName("message")
    @Expose val message: String,
    @SerializedName("request")
    @Expose
    val requestData: RequestData,
    @SerializedName("response")
    @Expose
    val passData: List<PassData>
)
