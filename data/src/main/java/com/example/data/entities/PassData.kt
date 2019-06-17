package com.example.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "passes")
data class PassData(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @SerializedName("risetime")
    @Expose val risetime: String,
    @SerializedName("duration") @Expose val duration: String
)
