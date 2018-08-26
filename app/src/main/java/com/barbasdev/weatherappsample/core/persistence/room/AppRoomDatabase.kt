package com.barbasdev.weatherappsample.core.persistence.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.barbasdev.weatherappsample.di.DatabaseConstants

@Database(
        entities = [
            WeatherRoom::class
        ],
        version = DatabaseConstants.ROOM_DB_SCHEMA
)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao
}
