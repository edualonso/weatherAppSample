package com.barbasdev.weatherappsample.core.persistence.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.barbasdev.weatherappsample.di.DatabaseConstants
import com.barbasdev.weatherappsample.di.dagger.modules.DatabaseModule

@Database(
        entities = [
            WeatherRoomDelegate::class
        ],
        version = DatabaseConstants.ROOM_DB_SCHEMA
)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao
}
