package com.barbasdev.weatherappsample.di.modules

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
            "http://api.apixu.com/v1/"

    @Provides
    @Named(NetworkModule.APIXU_API_KEY)
    fun providesApixuApiKey(): String =
            "11682c59698444f6b59160534171912"

    @Provides
    @Named(NetworkModule.OPENWEATHER_BASE_URL)
    fun providesOpenweatherBaseUrl(): String =
            "http://api.openweathermap.org/data/2.5/"

    @Provides
    @Named(NetworkModule.OPENWEATHER_API_KEY)
    fun providesOpenWeatherApiKey(): String =
            "75805b09ea06260c9eb71391b785f444"

}