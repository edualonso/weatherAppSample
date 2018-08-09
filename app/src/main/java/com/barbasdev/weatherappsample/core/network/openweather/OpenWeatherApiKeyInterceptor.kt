package com.barbasdev.weatherappsample.core.network.openweather

import com.barbasdev.weatherappsample.core.network.ApiKeyInterceptor
import com.barbasdev.weatherappsample.di.NetworkConstants

/**
 *
 */
class OpenWeatherApiKeyInterceptor(
        apiKey: String
) : ApiKeyInterceptor(
        NetworkConstants.OPENWEATHER_API_KEY,
        apiKey
)