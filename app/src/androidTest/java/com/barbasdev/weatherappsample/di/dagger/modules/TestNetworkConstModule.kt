package com.barbasdev.weatherappsample.di.dagger.modules

import com.barbasdev.weatherappsample.di.NetworkConstants
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 *
 */
@Module
class TestNetworkConstModule {

    @Provides
    @Named(NetworkConstants.APIXU_BASE_URL)
    fun providesApixuBaseUrl(): String =
            "http://localhost:$SERVER_PORT/"

    @Provides
    @Named(NetworkConstants.APIXU_API_KEY)
    fun providesApixuApiKey(): String =
            NetworkConstants.CONST_APIXU_API_KEY

    @Provides
    @Named(NetworkConstants.OPENWEATHER_BASE_URL)
    fun providesOpenweatherBaseUrl(): String =
            "http://localhost:$SERVER_PORT/"

    @Provides
    @Named(NetworkConstants.OPENWEATHER_API_KEY)
    fun providesOpenWeatherApiKey(): String =
            NetworkConstants.CONST_OPENWEATHER_API_KEY

    companion object {
        const val SERVER_PORT = 6666
    }
}