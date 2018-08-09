package com.barbasdev.weatherappsample.core.network.apixu

import com.barbasdev.weatherappsample.core.network.ApiKeyInterceptor
import com.barbasdev.weatherappsample.di.NetworkConstants
import com.barbasdev.weatherappsample.di.dagger.modules.NetworkModule

/**
 *
 */
class ApixuApiKeyInterceptor(
        apiKey: String
) : ApiKeyInterceptor(
        NetworkConstants.APIXU_API_KEY,
        apiKey
)