package com.barbasdev.weatherappsample.di.dagger.modules

import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 *
 */
@Module
class NetworkConstModule {

    @Provides
    @Named(NetworkModule.APIXU_BASE_URL)
    fun providesApixuBaseUrl(): String =
            CONST_APIXU_BASE_URL

    @Provides
    @Named(NetworkModule.APIXU_API_KEY)
    fun providesApixuApiKey(): String =
            CONST_APIXU_API_KEY

    @Provides
    @Named(NetworkModule.OPENWEATHER_BASE_URL)
    fun providesOpenweatherBaseUrl(): String =
            CONST_OPENWEATHER_BASE_URL

    @Provides
    @Named(NetworkModule.OPENWEATHER_API_KEY)
    fun providesOpenWeatherApiKey(): String =
            CONST_OPENWEATHER_API_KEY


    companion object {
        const val CONST_APIXU_BASE_URL = "http://api.apixu.com/v1/"
        const val CONST_APIXU_API_KEY = "11682c59698444f6b59160534171912"
        const val CONST_OPENWEATHER_BASE_URL = "http://api.openweathermap.org/data/2.5/"
        const val CONST_OPENWEATHER_API_KEY = "75805b09ea06260c9eb71391b785f444"
    }
}