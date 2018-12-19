package com.example.jasongomez.isschallenge.data.db

import android.arch.persistence.room.*
import com.example.jasongomez.isschallenge.data.entities.RequestData

@Dao
interface RequestsDao {

    @Query("SELECT * FROM requests")
    fun getRequests(): List<RequestData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllRequests(requests: List<RequestData>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveRequest(request: RequestData)

    @Delete
    fun removeRequest(request: RequestData)
}