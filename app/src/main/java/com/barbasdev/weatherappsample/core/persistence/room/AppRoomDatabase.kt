package com.barbasdev.weatherappsample.core.persistence.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.barbasdev.weatherappsample.di.modules.DatabaseModule

@Database(
        entities = [
            WeatherRoomDelegate::class
        ],
        version = DatabaseModule.ROOM_DB_SCHEMA
)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao
}