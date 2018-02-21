package com.barbasdev.weatherappsample.di.modules

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
                .schemaVersion(DB_SCHEMA)
                .deleteRealmIfMigrationNeeded()
                .build()
    }

    companion object {
        const val DB_SCHEMA = 1L
    }
}