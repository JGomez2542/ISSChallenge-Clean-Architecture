package com.example.jasongomez.isschallenge.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.domain.common.DATABASE_VERSION
import com.example.jasongomez.isschallenge.data.entities.PassData
import com.example.jasongomez.isschallenge.data.entities.RequestData

@Database(entities = arrayOf(PassData::class, RequestData::class), version = com.example.domain.common.DATABASE_VERSION)
abstract class PassesDatabase : RoomDatabase() {

    abstract fun getPassesDao(): PassesDao
    abstract fun getRequestsDao(): RequestsDao
}