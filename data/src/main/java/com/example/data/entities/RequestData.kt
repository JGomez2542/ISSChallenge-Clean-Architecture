package com.example.data.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "requests")
data class RequestData(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @SerializedName("latitude")
    @Expose
    val latitude: String,
    @SerializedName("longitude")
    @Expose
    val longitude: String,
    @SerializedName("altitude")
    @Expose
    val altitude: String,
    @SerializedName("passes")
    @Expose
    val passes: String,
    @SerializedName("datetime")
    @Expose
    val datetime: String
)
