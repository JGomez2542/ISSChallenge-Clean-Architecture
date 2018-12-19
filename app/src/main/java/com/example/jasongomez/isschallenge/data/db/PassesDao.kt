package com.example.jasongomez.isschallenge.data.db

import android.arch.persistence.room.*
import com.example.jasongomez.isschallenge.data.entities.PassData
import io.reactivex.Flowable
import io.reactivex.Single

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