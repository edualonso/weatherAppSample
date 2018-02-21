package com.barbasdev.weatherappsample.core.network.openweather

import com.barbasdev.weatherappsample.core.network.ApiKeyInterceptor
import com.barbasdev.weatherappsample.di.modules.NetworkModule
import javax.inject.Inject
import javax.inject.Named

/**
 *
 */
class OpenWeatherApiKeyInterceptor @Inject constructor(
        @Named(NetworkModule.OPENWEATHER_API_KEY) val apiKey: String
) : ApiKeyInterceptor(
        NetworkModule.OPENWEATHER_API_KEY,
        apiKey
)