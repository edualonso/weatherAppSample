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
import com.barbasdev.weatherappsample.di.dagger.modules.*
import io.realm.RealmConfiguration
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext

/**
 *
 */
object TestModules {

    // constants used in the network module
    val networkTestConstModule: Module = applicationContext {
        bean(NetworkModule.APIXU_BASE_URL) {
            "http://localhost:${TestNetworkConstModule.SERVER_PORT}/"
        }
        bean(NetworkModule.APIXU_API_KEY) {
            "11682c59698444f6b59160534171912"
        }
        bean(NetworkModule.OPENWEATHER_BASE_URL) {
            "http://localhost:${TestNetworkConstModule.SERVER_PORT}/"
        }
        bean(NetworkModule.OPENWEATHER_API_KEY) {
            "75805b09ea06260c9eb71391b785f444"
        }
    }


    // database module
    val databaseTestModule: Module = applicationContext {

        // realm
        bean(DatabaseModule.REALM_DB_APIXU_KOIN) {
            RealmConfiguration.Builder()
                    .inMemory()
                    .name(DatabaseModule.REALM_DB_APIXU_KOIN)
                    .schemaVersion(DatabaseModule.REALM_DB_SCHEMA)
                    .deleteRealmIfMigrationNeeded()
                    .build()
        }
        bean(DatabaseModule.REALM_DB_OPENWEATHER_KOIN) {
            RealmConfiguration.Builder()
                    .inMemory()
                    .name(DatabaseModule.REALM_DB_OPENWEATHER_KOIN)
                    .schemaVersion(DatabaseModule.REALM_DB_SCHEMA)
                    .deleteRealmIfMigrationNeeded()
                    .build()
        }


        // room
        bean(DatabaseModule.ROOM_DB_APIXU_DAO_KOIN) {
            Room.inMemoryDatabaseBuilder(androidApplication(), AppRoomDatabase::class.java)
                    .build()
                    .weatherDao()
        }
        bean(DatabaseModule.ROOM_DB_OPENWEATHER_DAO_KOIN) {
            Room.inMemoryDatabaseBuilder(androidApplication(), AppRoomDatabase::class.java)
                    .build()
                    .weatherDao()
        }
    }


    // repository module
    val repositoryTestModule: Module = applicationContext {

        // memory
        bean(RepositoryModule.REPOSITORY_MEMORY_APIXU) {
            //            MemoryRepositoryDelegate(get<ApixuApiClientDelegate>()) as Repository
            MemoryRepositoryDelegate(get(NetworkModule.APIXU_API_CLIENT)) as Repository
        }
        bean(RepositoryModule.REPOSITORY_MEMORY_OPENWEATHER) {
            //            MemoryRepositoryDelegate(get<OpenWeatherApiClientDelegate>()) as Repository
            MemoryRepositoryDelegate(get(NetworkModule.OPENWEATHER_API_CLIENT)) as Repository
        }


        // room
        bean(RepositoryModule.REPOSITORY_ROOM_APIXU) {
            //            RoomRepositoryDelegate(get<ApixuApiClientDelegate>(), get(DatabaseModule.ROOM_DB_APIXU_DAO)) as Repository
            RoomRepositoryDelegate(get(NetworkModule.APIXU_API_CLIENT), get(DatabaseModule.ROOM_DB_APIXU_DAO_KOIN)) as Repository
        }
        bean(RepositoryModule.REPOSITORY_ROOM_OPENWEATHER) {
            //            RoomRepositoryDelegate(get<OpenWeatherApiClientDelegate>(), get(DatabaseModule.ROOM_DB_OPENWEATHER_DAO)) as Repository
            RoomRepositoryDelegate(get(NetworkModule.OPENWEATHER_API_CLIENT), get(DatabaseModule.ROOM_DB_OPENWEATHER_DAO_KOIN)) as Repository
        }


        // realm
        bean(RepositoryModule.REPOSITORY_REALM_APIXU) {
            //            RealmRepositoryDelegate(get<ApixuApiClientDelegate>(), get(DatabaseModule.REALM_DB_APIXU)) as Repository
            RealmRepositoryDelegate(get(NetworkModule.APIXU_API_CLIENT), get(DatabaseModule.REALM_DB_APIXU_KOIN)) as Repository
        }
        bean(RepositoryModule.REPOSITORY_REALM_OPENWEATHER) {
            //            RealmRepositoryDelegate(get<OpenWeatherApiClientDelegate>(), get(DatabaseModule.REALM_DB_OPENWEATHER)) as Repository
            RealmRepositoryDelegate(get(NetworkModule.OPENWEATHER_API_CLIENT), get(DatabaseModule.REALM_DB_OPENWEATHER_KOIN)) as Repository
        }
    }
}