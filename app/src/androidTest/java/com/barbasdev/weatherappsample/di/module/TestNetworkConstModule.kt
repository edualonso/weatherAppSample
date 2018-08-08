package com.barbasdev.weatherappsample.di.module

import com.barbasdev.weatherappsample.di.dagger.modules.NetworkModule
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 *
 */
@Module
class TestNetworkConstModule {

    @Provides
    @Named(NetworkModule.APIXU_BASE_URL)
    fun providesApixuBaseUrl(): String =
            "http://localhost:$SERVER_PORT/"

    @Provides
    @Named(NetworkModule.APIXU_API_KEY)
    fun providesApixuApiKey(): String =
            "11682c59698444f6b59160534171912"

    @Provides
    @Named(NetworkModule.OPENWEATHER_BASE_URL)
    fun providesOpenweatherBaseUrl(): String =
            "http://localhost:$SERVER_PORT/"

    @Provides
    @Named(NetworkModule.OPENWEATHER_API_KEY)
    fun providesOpenWeatherApiKey(): String =
            "75805b09ea06260c9eb71391b785f444"

    companion object {
        const val SERVER_PORT = 6666
    }
}