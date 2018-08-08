package com.barbasdev.weatherappsample.core.network.openweather

import com.barbasdev.weatherappsample.core.network.ApiKeyInterceptor
import com.barbasdev.weatherappsample.di.dagger.modules.NetworkModule

/**
 *
 */
class OpenWeatherApiKeyInterceptor(
        apiKey: String
) : ApiKeyInterceptor(
        NetworkModule.OPENWEATHER_API_KEY,
        apiKey
)