package com.example.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.data.entities.PassData
import com.example.data.entities.RequestData
import com.example.core.common.DATABASE_VERSION

@Database(entities = arrayOf(PassData::class, RequestData::class), version = DATABASE_VERSION)
abstract class PassesDatabase : RoomDatabase() {

    abstract fun getPassesDao(): com.example.data.db.PassesDao
    abstract fun getRequestsDao(): RequestsDao
}