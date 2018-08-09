package com.barbasdev.weatherappsample.di.koin

import android.arch.persistence.room.Room
import com.barbasdev.weatherappsample.core.network.ApiClient
import com.barbasdev.weatherappsample.core.network.apixu.ApixuApiClientDelegate
import com.barbasdev.weatherappsample.core.network.apixu.ApixuApiKeyInterceptor
import com.barbasdev.weatherappsample.core.network.apixu.ApixuWeatherService
import com.barbasdev.weatherappsample.core.network.openweather.OpenWeatherApiClientDelegate
import com.barbasdev.weatherappsample.core.network.openweather.OpenWeatherApiKeyInterceptor
import com.barbasdev.weatherappsample.core.network.openweather.OpenWeatherService
import com.barbasdev.weatherappsample.core.persistence.Repository
import com.barbasdev.weatherappsample.core.persistence.memory.MemoryRepositoryDelegate
import com.barbasdev.weatherappsample.core.persistence.realm.RealmRepositoryDelegate
import com.barbasdev.weatherappsample.core.persistence.room.AppRoomDatabase
import com.barbasdev.weatherappsample.core.persistence.room.RoomRepositoryDelegate
import com.barbasdev.weatherappsample.di.DatabaseConstants
import com.barbasdev.weatherappsample.di.NetworkConstants
import com.barbasdev.weatherappsample.di.RepositoryConstants
import com.barbasdev.weatherappsample.di.dagger.modules.DatabaseModule
import com.barbasdev.weatherappsample.di.dagger.modules.NetworkConstModule
import com.barbasdev.weatherappsample.di.dagger.modules.NetworkModule
import com.barbasdev.weatherappsample.di.dagger.modules.RepositoryModule
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
            ApixuApiClientDelegate(get()) as ApiClient
//            ApixuApiClientDelegate(get())
        }

        // Openweather api client
        bean(NetworkConstants.OPENWEATHER_API_CLIENT) {
            OpenWeatherApiClientDelegate(get()) as ApiClient
//            OpenWeatherApiClientDelegate(get())
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
//            MemoryRepositoryDelegate(get<ApixuApiClientDelegate>()) as Repository
            MemoryRepositoryDelegate(get(NetworkConstants.APIXU_API_CLIENT)) as Repository
        }
        bean(RepositoryConstants.REPOSITORY_MEMORY_OPENWEATHER) {
//            MemoryRepositoryDelegate(get<OpenWeatherApiClientDelegate>()) as Repository
            MemoryRepositoryDelegate(get(NetworkConstants.OPENWEATHER_API_CLIENT)) as Repository
        }


        // room
        bean(RepositoryConstants.REPOSITORY_ROOM_APIXU) {
//            RoomRepositoryDelegate(get<ApixuApiClientDelegate>(), get(DatabaseConstants.ROOM_DB_APIXU_DAO_KOIN)) as Repository
            RoomRepositoryDelegate(get(NetworkConstants.APIXU_API_CLIENT), get(DatabaseConstants.ROOM_DB_APIXU_DAO_KOIN)) as Repository
        }
        bean(RepositoryConstants.REPOSITORY_ROOM_OPENWEATHER) {
//            RoomRepositoryDelegate(get<OpenWeatherApiClientDelegate>(), get(DatabaseConstants.ROOM_DB_OPENWEATHER_DAO_KOIN)) as Repository
            RoomRepositoryDelegate(get(NetworkConstants.OPENWEATHER_API_CLIENT), get(DatabaseConstants.ROOM_DB_OPENWEATHER_DAO_KOIN)) as Repository
        }


        // realm
        bean(RepositoryConstants.REPOSITORY_REALM_APIXU) {
//            RealmRepositoryDelegate(get<ApixuApiClientDelegate>(), get(DatabaseConstants.REALM_DB_APIXU_KOIN)) as Repository
            RealmRepositoryDelegate(get(NetworkConstants.APIXU_API_CLIENT), get(DatabaseConstants.REALM_DB_APIXU_KOIN)) as Repository
        }
        bean(RepositoryConstants.REPOSITORY_REALM_OPENWEATHER) {
//            RealmRepositoryDelegate(get<OpenWeatherApiClientDelegate>(), get(DatabaseConstants.REALM_DB_OPENWEATHER_KOIN)) as Repository
            RealmRepositoryDelegate(get(NetworkConstants.OPENWEATHER_API_CLIENT), get(DatabaseConstants.REALM_DB_OPENWEATHER_KOIN)) as Repository
        }
    }
}