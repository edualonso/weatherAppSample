package com.barbasdev.weatherappsample.di.dagger.modules

import com.barbasdev.weatherappsample.core.network.ApiClient
import com.barbasdev.weatherappsample.core.network.apixu.ApixuApiClient
import com.barbasdev.weatherappsample.core.network.apixu.ApixuApiKeyInterceptor
import com.barbasdev.weatherappsample.core.network.apixu.ApixuWeatherService
import com.barbasdev.weatherappsample.core.network.openweather.OpenWeatherApiClient
import com.barbasdev.weatherappsample.core.network.openweather.OpenWeatherApiKeyInterceptor
import com.barbasdev.weatherappsample.core.network.openweather.OpenWeatherService
import com.barbasdev.weatherappsample.di.NetworkConstants
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

/**
 *
 */
@Module
class NetworkModule {


    //--------------------------------------------------------------------------------
    // Services
    //--------------------------------------------------------------------------------

    @Provides
    @Singleton
    fun providesApixuWeatherService(
            @Named(NetworkConstants.APIXU_BASE_URL) apixuBaseUrl: String,
            @Named(NetworkConstants.APIXU_API_KEY) apixuApiKey: String
    ): ApixuWeatherService {
        return NetworkConstants.getRetrofit(apixuBaseUrl, ApixuApiKeyInterceptor(apixuApiKey))
                .create(ApixuWeatherService::class.java)
    }

    @Provides
    @Singleton
    fun providesOpenWeatherService(
            @Named(NetworkConstants.OPENWEATHER_BASE_URL) openWeatherBaseUrl: String,
            @Named(NetworkConstants.OPENWEATHER_API_KEY) openWeatherApiKey: String
    ): OpenWeatherService {
        return NetworkConstants.getRetrofit(openWeatherBaseUrl, OpenWeatherApiKeyInterceptor(openWeatherApiKey))
                .create(OpenWeatherService::class.java)
    }


    //--------------------------------------------------------------------------------
    // API clients
    //--------------------------------------------------------------------------------

    @Provides
    @Named(NetworkConstants.APIXU_API_CLIENT)
    @Singleton
    fun providesApixuApiClient(delegate: ApixuApiClient): ApiClient =
            delegate

    @Provides
    @Named(NetworkConstants.OPENWEATHER_API_CLIENT)
    @Singleton
    fun providesOpenWeatherApiClient(delegate: OpenWeatherApiClient): ApiClient =
            delegate

}