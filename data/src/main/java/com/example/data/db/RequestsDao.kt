package com.example.data.db

import android.arch.persistence.room.*
import com.example.data.entities.RequestData

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