package com.barbasdev.weatherappsample.di.dagger.modules

import android.arch.persistence.room.Room
import android.content.Context
import com.barbasdev.weatherappsample.core.persistence.room.AppRoomDatabase
import com.barbasdev.weatherappsample.core.persistence.room.WeatherDao
import com.barbasdev.weatherappsample.di.DatabaseConstants
import dagger.Module
import dagger.Provides
import io.realm.RealmConfiguration
import javax.inject.Named


/**
 *
 */
@Module
class DatabaseModule {

    @Provides
    @Named(DatabaseConstants.REALM_DB_APIXU_DAGGER)
    fun providesRealmConfigurationApixu(): RealmConfiguration {
        return RealmConfiguration.Builder()
                .name(DatabaseConstants.REALM_DB_APIXU_DAGGER)
                .schemaVersion(DatabaseConstants.REALM_DB_SCHEMA)
                .deleteRealmIfMigrationNeeded()
                .build()
    }

    @Provides
    @Named(DatabaseConstants.REALM_DB_OPENWEATHER_DAGGER)
    fun providesRealmConfigurationOpenWeather(): RealmConfiguration {
        return RealmConfiguration.Builder()
                .name(DatabaseConstants.REALM_DB_OPENWEATHER_DAGGER)
                .schemaVersion(DatabaseConstants.REALM_DB_SCHEMA)
                .deleteRealmIfMigrationNeeded()
                .build()
    }

    @Provides
    @Named(DatabaseConstants.ROOM_DB_APIXU_DAO_DAGGER)
    fun providesWeatherDaoApixu(context: Context): WeatherDao {
        return Room
                .databaseBuilder(context, AppRoomDatabase::class.java, "weatherAppRoomDatabaseApixu")
                .build()
                .weatherDao()
    }

    @Provides
    @Named(DatabaseConstants.ROOM_DB_OPENWEATHER_DAO_DAGGER)
    fun providesWeatherDaoOpenWeather(context: Context): WeatherDao {
        return Room
                .databaseBuilder(context, AppRoomDatabase::class.java, "weatherAppRoomDatabaseOpenWeather")
                .build()
                .weatherDao()
    }
}
