package com.barbasdev.weatherappsample.di.koin

import android.arch.persistence.room.Room
import com.barbasdev.weatherappsample.core.persistence.room.AppRoomDatabase
import com.barbasdev.weatherappsample.di.DatabaseConstants
import com.barbasdev.weatherappsample.di.NetworkConstants
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
        bean(NetworkConstants.APIXU_BASE_URL) {
            "http://localhost:${TestNetworkConstModule.SERVER_PORT}/"
        }
        bean(NetworkConstants.APIXU_API_KEY) {
            "11682c59698444f6b59160534171912"
        }
        bean(NetworkConstants.OPENWEATHER_BASE_URL) {
            "http://localhost:${TestNetworkConstModule.SERVER_PORT}/"
        }
        bean(NetworkConstants.OPENWEATHER_API_KEY) {
            "75805b09ea06260c9eb71391b785f444"
        }
    }


    // database module
    val databaseTestModule: Module = applicationContext {

        // realm
        bean(DatabaseConstants.REALM_DB_APIXU_KOIN) {
            RealmConfiguration.Builder()
                    .inMemory()
                    .name(DatabaseConstants.REALM_DB_APIXU_KOIN)
                    .schemaVersion(DatabaseConstants.REALM_DB_SCHEMA)
                    .deleteRealmIfMigrationNeeded()
                    .build()
        }
        bean(DatabaseConstants.REALM_DB_OPENWEATHER_KOIN) {
            RealmConfiguration.Builder()
                    .inMemory()
                    .name(DatabaseConstants.REALM_DB_OPENWEATHER_KOIN)
                    .schemaVersion(DatabaseConstants.REALM_DB_SCHEMA)
                    .deleteRealmIfMigrationNeeded()
                    .build()
        }


        // room
        bean(DatabaseConstants.ROOM_DB_APIXU_DAO_KOIN) {
            Room.inMemoryDatabaseBuilder(androidApplication(), AppRoomDatabase::class.java)
                    .build()
                    .weatherDao()
        }
        bean(DatabaseConstants.ROOM_DB_OPENWEATHER_DAO_KOIN) {
            Room.inMemoryDatabaseBuilder(androidApplication(), AppRoomDatabase::class.java)
                    .build()
                    .weatherDao()
        }
    }
}
