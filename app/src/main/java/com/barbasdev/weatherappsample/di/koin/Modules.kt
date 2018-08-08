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
        bean(NetworkModule.APIXU_BASE_URL) {
            NetworkConstModule.CONST_APIXU_BASE_URL
        }
        bean(NetworkModule.APIXU_API_KEY) {
            NetworkConstModule.CONST_APIXU_API_KEY
        }
        bean(NetworkModule.OPENWEATHER_BASE_URL) {
            NetworkConstModule.CONST_OPENWEATHER_BASE_URL
        }
        bean(NetworkModule.OPENWEATHER_API_KEY) {
            NetworkConstModule.CONST_OPENWEATHER_API_KEY
        }
    }


    // network module
    val networkModule: Module = applicationContext {

        // Apixu service
        bean {
            NetworkModule.getRetrofit(get(
                    NetworkModule.APIXU_BASE_URL),
                    ApixuApiKeyInterceptor(get(NetworkModule.APIXU_API_KEY))
            ).create(ApixuWeatherService::class.java)
        }

        // Openweather service
        bean {
            NetworkModule.getRetrofit(get(
                    NetworkModule.OPENWEATHER_BASE_URL),
                    OpenWeatherApiKeyInterceptor(get(NetworkModule.OPENWEATHER_API_KEY))
            ).create(OpenWeatherService::class.java)
        }

        // Apixu api client
        bean(NetworkModule.APIXU_API_CLIENT) {
            ApixuApiClientDelegate(get()) as ApiClient
        }

        // Openweather api client
        bean(NetworkModule.OPENWEATHER_API_CLIENT) {
            OpenWeatherApiClientDelegate(get()) as ApiClient
        }
    }


    // database module
    val databaseModule: Module = applicationContext {

        // realm
        bean {
            RealmConfiguration.Builder()
                    .name("weather.realm")
                    .schemaVersion(DatabaseModule.REALM_DB_SCHEMA)
                    .deleteRealmIfMigrationNeeded()
                    .build()
        }


        // room
        bean {
            Room.databaseBuilder(androidApplication(), AppRoomDatabase::class.java, "weatherAppRoomDatabase")
                    .build()
                    .weatherDao()
        }
    }


    // repository module
    val repositoryModule: Module = applicationContext {

        // memory
        bean(RepositoryModule.REPOSITORY_MEMORY_APIXU) {
            MemoryRepositoryDelegate(get<ApixuApiClientDelegate>()) as Repository
        }
        bean(RepositoryModule.REPOSITORY_MEMORY_OPENWEATHER) {
            MemoryRepositoryDelegate(get<OpenWeatherApiClientDelegate>()) as Repository
        }


        // room
        bean(RepositoryModule.REPOSITORY_ROOM_APIXU) {
            RoomRepositoryDelegate(get<ApixuApiClientDelegate>(), get()) as Repository
        }
        bean(RepositoryModule.REPOSITORY_ROOM_OPENWEATHER) {
            RoomRepositoryDelegate(get<OpenWeatherApiClientDelegate>(), get()) as Repository
        }


        // realm
        bean(RepositoryModule.REPOSITORY_REALM_APIXU) {
            RealmRepositoryDelegate(get<ApixuApiClientDelegate>(), get()) as Repository
        }
        bean(RepositoryModule.REPOSITORY_REALM_OPENWEATHER) {
            RealmRepositoryDelegate(get<OpenWeatherApiClientDelegate>(), get()) as Repository
        }
    }
}