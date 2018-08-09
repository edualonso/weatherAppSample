package com.barbasdev.weatherappsample.di.dagger.modules

import android.arch.persistence.room.Room
import android.content.Context
import com.barbasdev.weatherappsample.core.persistence.room.AppRoomDatabase
import com.barbasdev.weatherappsample.core.persistence.room.WeatherDao
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
    @Named(DatabaseModule.REALM_DB_APIXU_DAGGER)
    fun providesRealmConfigurationApixu(): RealmConfiguration {
        return RealmConfiguration.Builder()
                .name(DatabaseModule.REALM_DB_APIXU_DAGGER)
                .schemaVersion(REALM_DB_SCHEMA)
                .deleteRealmIfMigrationNeeded()
                .build()
    }

    @Provides
    @Named(DatabaseModule.REALM_DB_OPENWEATHER_DAGGER)
    fun providesRealmConfigurationOpenWeather(): RealmConfiguration {
        return RealmConfiguration.Builder()
                .name(DatabaseModule.REALM_DB_OPENWEATHER_DAGGER)
                .schemaVersion(REALM_DB_SCHEMA)
                .deleteRealmIfMigrationNeeded()
                .build()
    }

    @Provides
    @Named(DatabaseModule.ROOM_DB_APIXU_DAO_DAGGER)
    fun providesWeatherDaoApixu(context: Context): WeatherDao {
        return Room
                .databaseBuilder(context, AppRoomDatabase::class.java, "weatherAppRoomDatabaseApixu")
                .build()
                .weatherDao()
    }

    @Provides
    @Named(DatabaseModule.ROOM_DB_OPENWEATHER_DAO_DAGGER)
    fun providesWeatherDaoOpenWeather(context: Context): WeatherDao {
        return Room
                .databaseBuilder(context, AppRoomDatabase::class.java, "weatherAppRoomDatabaseOpenWeather")
                .build()
                .weatherDao()
    }


    companion object {
        const val REALM_DB_SCHEMA = 1L
        const val REALM_DB_APIXU_DAGGER = "db_apixu_dagger.realm"
        const val REALM_DB_APIXU_KOIN = "db_apixu_koin.realm"
        const val REALM_DB_OPENWEATHER_DAGGER = "db_openweather_dagger.realm"
        const val REALM_DB_OPENWEATHER_KOIN = "db_openweather_koin.realm"

        const val ROOM_DB_SCHEMA = 1
        const val ROOM_DB_APIXU = "ROOM_DB_APIXU"
        const val ROOM_DB_APIXU_DAO_DAGGER = "ROOM_DB_APIXU_DAO_DAGGER"
        const val ROOM_DB_APIXU_DAO_KOIN = "ROOM_DB_APIXU_DAO_KOIN"
        const val ROOM_DB_OPENWEATHER = "ROOM_DB_OPENWEATHER"
        const val ROOM_DB_OPENWEATHER_DAO_DAGGER = "ROOM_DB_OPENWEATHER_DAO_DAGGER"
        const val ROOM_DB_OPENWEATHER_DAO_KOIN = "ROOM_DB_OPENWEATHER_DAO_KOIN"
    }
}