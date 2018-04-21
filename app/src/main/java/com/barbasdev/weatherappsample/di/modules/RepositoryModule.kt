package com.barbasdev.weatherappsample.di.modules

import com.barbasdev.weatherappsample.core.network.ApiClientImpl
import com.barbasdev.weatherappsample.core.persistence.Repository
import com.barbasdev.weatherappsample.core.persistence.RepositoryImpl
import com.barbasdev.weatherappsample.core.persistence.memory.MemoryRepositoryDelegate
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

/**
 *
 */
@Module
class RepositoryModule {

    @Provides
    @Named(REPOSITORY_MEMORY_APIXU)
    @Singleton
    fun providesMemoryRepositoryApixu(
            @Named(NetworkModule.APIXU_API_CLIENT) apiClient: ApiClientImpl
    ): Repository =
            RepositoryImpl(MemoryRepositoryDelegate(apiClient))

    @Provides
    @Named(REPOSITORY_MEMORY_OPENWEATHER)
    @Singleton
    fun providesMemoryRepositoryOpenWeather(
            @Named(NetworkModule.OPENWEATHER_API_CLIENT) apiClient: ApiClientImpl
    ): Repository =
            RepositoryImpl(MemoryRepositoryDelegate(apiClient))


    companion object {
        const val REPOSITORY_MEMORY_APIXU           = "REPOSITORY_MEMORY_APIXU"
        const val REPOSITORY_MEMORY_OPENWEATHER     = "REPOSITORY_MEMORY_OPENWEATHER"
    }
}