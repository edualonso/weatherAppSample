package com.barbasdev.weatherappsample.di.dagger.modules

import com.barbasdev.weatherappsample.core.network.ApiClient
import com.barbasdev.weatherappsample.core.persistence.Repository
import com.barbasdev.weatherappsample.core.persistence.memory.MemoryRepositoryDelegate
import com.barbasdev.weatherappsample.core.persistence.realm.RealmRepositoryDelegate
import com.barbasdev.weatherappsample.core.persistence.room.RoomRepositoryDelegate
import com.barbasdev.weatherappsample.core.persistence.room.WeatherDao
import dagger.Module
import dagger.Provides
import io.realm.RealmConfiguration
import javax.inject.Named
import javax.inject.Singleton

/**
 *
 */
@Module
class RepositoryModule {

    // MEMORY REPOS

    @Provides
    @Named(REPOSITORY_MEMORY_APIXU)
    @Singleton
    fun providesMemoryRepositoryApixu(
            @Named(NetworkModule.APIXU_API_CLIENT) apiClient: ApiClient
    ): Repository =
            MemoryRepositoryDelegate(apiClient)

    @Provides
    @Named(REPOSITORY_MEMORY_OPENWEATHER)
    @Singleton
    fun providesMemoryRepositoryOpenWeather(
            @Named(NetworkModule.OPENWEATHER_API_CLIENT) apiClient: ApiClient
    ): Repository =
            MemoryRepositoryDelegate(apiClient)


    // ROOM REPOS

    @Provides
    @Named(REPOSITORY_ROOM_APIXU)
    @Singleton
    fun providesRoomRepositoryApixu(
            @Named(NetworkModule.APIXU_API_CLIENT) apiClient: ApiClient,
            @Named(DatabaseModule.ROOM_DB_APIXU_DAO_DAGGER) weatherDao: WeatherDao
    ): Repository =
            RoomRepositoryDelegate(apiClient, weatherDao)

    @Provides
    @Named(REPOSITORY_ROOM_OPENWEATHER)
    @Singleton
    fun providesRoomRepositoryOpenWeather(
            @Named(NetworkModule.OPENWEATHER_API_CLIENT) apiClient: ApiClient,
            @Named(DatabaseModule.ROOM_DB_OPENWEATHER_DAO_DAGGER) weatherDao: WeatherDao
    ): Repository =
            RoomRepositoryDelegate(apiClient, weatherDao)


    // REALM REPOS

    @Provides
    @Named(REPOSITORY_REALM_APIXU)
    @Singleton
    fun providesRealmRepositoryApixu(
            @Named(NetworkModule.APIXU_API_CLIENT) apiClient: ApiClient,
            @Named(DatabaseModule.REALM_DB_APIXU_DAGGER) realmConfiguration: RealmConfiguration
    ): Repository =
            RealmRepositoryDelegate(apiClient, realmConfiguration)

    @Provides
    @Named(REPOSITORY_REALM_OPENWEATHER)
    @Singleton
    fun providesRealmRepositoryOpenWeather(
            @Named(NetworkModule.OPENWEATHER_API_CLIENT) apiClient: ApiClient,
            @Named(DatabaseModule.REALM_DB_OPENWEATHER_DAGGER) realmConfiguration: RealmConfiguration
    ): Repository =
            RealmRepositoryDelegate(apiClient, realmConfiguration)


    companion object {
        const val REPOSITORY_MEMORY_APIXU           = "REPOSITORY_MEMORY_APIXU"
        const val REPOSITORY_MEMORY_OPENWEATHER     = "REPOSITORY_MEMORY_OPENWEATHER"
        const val REPOSITORY_ROOM_APIXU             = "REPOSITORY_ROOM_APIXU"
        const val REPOSITORY_ROOM_OPENWEATHER       = "REPOSITORY_ROOM_OPENWEATHER"
        const val REPOSITORY_REALM_APIXU            = "REPOSITORY_REALM_APIXU"
        const val REPOSITORY_REALM_OPENWEATHER      = "REPOSITORY_REALM_OPENWEATHER"
    }
}