package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.entities.PassData
import com.example.data.entities.RequestData
import com.example.core.common.DATABASE_VERSION

@Database(entities = [PassData::class, RequestData::class], version = DATABASE_VERSION)
abstract class PassesDatabase : RoomDatabase() {

    abstract fun getPassesDao(): PassesDao
    abstract fun getRequestsDao(): RequestsDao
}