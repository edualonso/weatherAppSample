package com.barbasdev.weatherappsample.di.koin

import android.arch.persistence.room.Room
import com.barbasdev.weatherappsample.core.network.ApiClient
import com.barbasdev.weatherappsample.core.network.apixu.ApixuApiClient
import com.barbasdev.weatherappsample.core.network.apixu.ApixuApiKeyInterceptor
import com.barbasdev.weatherappsample.core.network.apixu.ApixuWeatherService
import com.barbasdev.weatherappsample.core.network.openweather.OpenWeatherApiClient
import com.barbasdev.weatherappsample.core.network.openweather.OpenWeatherApiKeyInterceptor
import com.barbasdev.weatherappsample.core.network.openweather.OpenWeatherService
import com.barbasdev.weatherappsample.core.persistence.Repository
import com.barbasdev.weatherappsample.core.persistence.memory.MemoryRepository
import com.barbasdev.weatherappsample.core.persistence.realm.RealmRepository
import com.barbasdev.weatherappsample.core.persistence.room.AppRoomDatabase
import com.barbasdev.weatherappsample.core.persistence.room.RoomRepository
import com.barbasdev.weatherappsample.di.DatabaseConstants
import com.barbasdev.weatherappsample.di.NetworkConstants
import com.barbasdev.weatherappsample.di.RepositoryConstants
import io.realm.RealmConfiguration
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext

/**
 *
 */
object Modules {

    // constants used in the network module
    val networkConstModule: Module = applicationContext {
        bean(NetworkConstants.APIXU_BASE_URL) {
            NetworkConstants.CONST_APIXU_BASE_URL
        }
        bean(NetworkConstants.APIXU_API_KEY) {
            NetworkConstants.CONST_APIXU_API_KEY
        }
        bean(NetworkConstants.OPENWEATHER_BASE_URL) {
            NetworkConstants.CONST_OPENWEATHER_BASE_URL
        }
        bean(NetworkConstants.OPENWEATHER_API_KEY) {
            NetworkConstants.CONST_OPENWEATHER_API_KEY
        }
    }


    // network module
    val networkModule: Module = applicationContext {

        // Apixu service
        bean {
            NetworkConstants.getRetrofit(get(
                    NetworkConstants.APIXU_BASE_URL),
                    ApixuApiKeyInterceptor(get(NetworkConstants.APIXU_API_KEY))
            ).create(ApixuWeatherService::class.java)
        }

        // Openweather service
        bean {
            NetworkConstants.getRetrofit(get(
                    NetworkConstants.OPENWEATHER_BASE_URL),
                    OpenWeatherApiKeyInterceptor(get(NetworkConstants.OPENWEATHER_API_KEY))
            ).create(OpenWeatherService::class.java)
        }

        // Apixu api client
        bean(NetworkConstants.APIXU_API_CLIENT) {
            ApixuApiClient(get()) as ApiClient
//            ApixuApiClient(get())
        }

        // Openweather api client
        bean(NetworkConstants.OPENWEATHER_API_CLIENT) {
            OpenWeatherApiClient(get()) as ApiClient
//            OpenWeatherApiClient(get())
        }
    }


    // database module
    val databaseModule: Module = applicationContext {

        // realm
        bean(DatabaseConstants.REALM_DB_APIXU_KOIN) {
            RealmConfiguration.Builder()
                    .name(DatabaseConstants.REALM_DB_APIXU_KOIN)
                    .schemaVersion(DatabaseConstants.REALM_DB_SCHEMA)
                    .deleteRealmIfMigrationNeeded()
                    .build()
        }
        bean(DatabaseConstants.REALM_DB_OPENWEATHER_KOIN) {
            RealmConfiguration.Builder()
                    .name(DatabaseConstants.REALM_DB_OPENWEATHER_KOIN)
                    .schemaVersion(DatabaseConstants.REALM_DB_SCHEMA)
                    .deleteRealmIfMigrationNeeded()
                    .build()
        }


        // room
        bean(DatabaseConstants.ROOM_DB_APIXU_DAO_KOIN) {
            Room.databaseBuilder(androidApplication(), AppRoomDatabase::class.java, DatabaseConstants.ROOM_DB_APIXU)
                    .build()
                    .weatherDao()
        }
        bean(DatabaseConstants.ROOM_DB_OPENWEATHER_DAO_KOIN) {
            Room.databaseBuilder(androidApplication(), AppRoomDatabase::class.java, DatabaseConstants.ROOM_DB_OPENWEATHER)
                    .build()
                    .weatherDao()
        }
    }


    // repository module
    val repositoryModule: Module = applicationContext {

        // memory
        bean(RepositoryConstants.REPOSITORY_MEMORY_APIXU) {
//            MemoryRepository(get<ApixuApiClient>()) as Repository
            MemoryRepository(get(NetworkConstants.APIXU_API_CLIENT)) as Repository
        }
        bean(RepositoryConstants.REPOSITORY_MEMORY_OPENWEATHER) {
//            MemoryRepository(get<OpenWeatherApiClient>()) as Repository
            MemoryRepository(get(NetworkConstants.OPENWEATHER_API_CLIENT)) as Repository
        }


        // room
        bean(RepositoryConstants.REPOSITORY_ROOM_APIXU) {
//            RoomRepository(get<ApixuApiClient>(), get(DatabaseConstants.ROOM_DB_APIXU_DAO_KOIN)) as Repository
            RoomRepository(get(NetworkConstants.APIXU_API_CLIENT), get(DatabaseConstants.ROOM_DB_APIXU_DAO_KOIN)) as Repository
        }
        bean(RepositoryConstants.REPOSITORY_ROOM_OPENWEATHER) {
//            RoomRepository(get<OpenWeatherApiClient>(), get(DatabaseConstants.ROOM_DB_OPENWEATHER_DAO_KOIN)) as Repository
            RoomRepository(get(NetworkConstants.OPENWEATHER_API_CLIENT), get(DatabaseConstants.ROOM_DB_OPENWEATHER_DAO_KOIN)) as Repository
        }


        // realm
        bean(RepositoryConstants.REPOSITORY_REALM_APIXU) {
//            RealmRepository(get<ApixuApiClient>(), get(DatabaseConstants.REALM_DB_APIXU_KOIN)) as Repository
            RealmRepository(get(NetworkConstants.APIXU_API_CLIENT), get(DatabaseConstants.REALM_DB_APIXU_KOIN)) as Repository
        }
        bean(RepositoryConstants.REPOSITORY_REALM_OPENWEATHER) {
//            RealmRepository(get<OpenWeatherApiClient>(), get(DatabaseConstants.REALM_DB_OPENWEATHER_KOIN)) as Repository
            RealmRepository(get(NetworkConstants.OPENWEATHER_API_CLIENT), get(DatabaseConstants.REALM_DB_OPENWEATHER_KOIN)) as Repository
        }
    }
}