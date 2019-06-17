package com.example.data.db

import androidx.room.*
import com.example.data.entities.PassData
import io.reactivex.Flowable

@Dao
interface PassesDao {

    @Query("SELECT * FROM passes")
    fun getPasses(): Flowable<List<PassData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllPasses(passes: List<PassData>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savePass(pass: PassData)

    @Delete
    fun removePass(pass: PassData)
}