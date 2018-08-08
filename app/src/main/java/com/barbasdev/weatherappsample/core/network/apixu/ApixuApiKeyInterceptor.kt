package com.barbasdev.weatherappsample.core.network.apixu

import com.barbasdev.weatherappsample.core.network.ApiKeyInterceptor
import com.barbasdev.weatherappsample.di.dagger.modules.NetworkModule

/**
 *
 */
class ApixuApiKeyInterceptor(
        apiKey: String
) : ApiKeyInterceptor(
        NetworkModule.APIXU_API_KEY,
        apiKey
)