package com.barbasdev.weatherappsample.di.modules

import android.arch.persistence.room.Room
import android.content.Context
import com.barbasdev.weatherappsample.core.persistence.room.AppRoomDatabase
import com.barbasdev.weatherappsample.core.persistence.room.WeatherDao
import dagger.Module
import dagger.Provides
import io.realm.RealmConfiguration


/**
 *
 */
@Module
class DatabaseModule {

    @Provides
    fun providesRealmConfiguration(): RealmConfiguration {
        return RealmConfiguration.Builder()
                .name("weather.realm")
                .schemaVersion(REALM_DB_SCHEMA)
                .deleteRealmIfMigrationNeeded()
                .build()
    }

    @Provides
    fun providesRoom(context: Context): AppRoomDatabase {
        return Room
                .databaseBuilder(context, AppRoomDatabase::class.java, "weatherAppRoomDatabase")
                .build()
    }

    @Provides
    fun providesWeatherDao(roomDatabase: AppRoomDatabase): WeatherDao {
        return roomDatabase.weatherDao()
    }


    companion object {
        const val REALM_DB_SCHEMA = 1L

        const val ROOM_DB_SCHEMA = 1
    }
}