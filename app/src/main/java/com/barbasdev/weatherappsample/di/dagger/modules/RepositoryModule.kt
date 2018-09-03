package com.barbasdev.weatherappsample.di.dagger.modules

import com.barbasdev.weatherappsample.core.network.ApiClient
import com.barbasdev.weatherappsample.core.persistence.Repository
import com.barbasdev.weatherappsample.core.persistence.memory.MemoryRepository
import com.barbasdev.weatherappsample.core.persistence.realm.RealmRepository
import com.barbasdev.weatherappsample.core.persistence.room.RoomRepository
import com.barbasdev.weatherappsample.core.persistence.room.WeatherDao
import com.barbasdev.weatherappsample.di.DatabaseConstants
import com.barbasdev.weatherappsample.di.NetworkConstants
import com.barbasdev.weatherappsample.di.RepositoryConstants
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
    @Named(RepositoryConstants.REPOSITORY_MEMORY_APIXU)
    @Singleton
    fun providesMemoryRepositoryApixu(
            @Named(NetworkConstants.APIXU_API_CLIENT) apiClient: ApiClient
    ): Repository =
            MemoryRepository(apiClient)

    @Provides
    @Named(RepositoryConstants.REPOSITORY_MEMORY_OPENWEATHER)
    @Singleton
    fun providesMemoryRepositoryOpenWeather(
            @Named(NetworkConstants.OPENWEATHER_API_CLIENT) apiClient: ApiClient
    ): Repository =
            MemoryRepository(apiClient)


    // ROOM REPOS

    @Provides
    @Named(RepositoryConstants.REPOSITORY_ROOM_APIXU)
    @Singleton
    fun providesRoomRepositoryApixu(
            @Named(NetworkConstants.APIXU_API_CLIENT) apiClient: ApiClient,
            @Named(DatabaseConstants.ROOM_DB_APIXU_DAO_DAGGER) weatherDao: WeatherDao
    ): Repository =
            RoomRepository(apiClient, weatherDao)

    @Provides
    @Named(RepositoryConstants.REPOSITORY_ROOM_OPENWEATHER)
    @Singleton
    fun providesRoomRepositoryOpenWeather(
            @Named(NetworkConstants.OPENWEATHER_API_CLIENT) apiClient: ApiClient,
            @Named(DatabaseConstants.ROOM_DB_OPENWEATHER_DAO_DAGGER) weatherDao: WeatherDao
    ): Repository =
            RoomRepository(apiClient, weatherDao)


    // REALM REPOS

    @Provides
    @Named(RepositoryConstants.REPOSITORY_REALM_APIXU)
    @Singleton
    fun providesRealmRepositoryApixu(
            @Named(NetworkConstants.APIXU_API_CLIENT) apiClient: ApiClient,
            @Named(DatabaseConstants.REALM_DB_APIXU_DAGGER) realmConfiguration: RealmConfiguration
    ): Repository =
            RealmRepository(apiClient, realmConfiguration)

    @Provides
    @Named(RepositoryConstants.REPOSITORY_REALM_OPENWEATHER)
    @Singleton
    fun providesRealmRepositoryOpenWeather(
            @Named(NetworkConstants.OPENWEATHER_API_CLIENT) apiClient: ApiClient,
            @Named(DatabaseConstants.REALM_DB_OPENWEATHER_DAGGER) realmConfiguration: RealmConfiguration
    ): Repository =
            RealmRepository(apiClient, realmConfiguration)
}
