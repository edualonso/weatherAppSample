package com.barbasdev.weatherappsample.core.network.apixu

import com.barbasdev.weatherappsample.core.network.ApiKeyInterceptor
import com.barbasdev.weatherappsample.di.modules.NetworkModule
import javax.inject.Inject
import javax.inject.Named

/**
 *
 */
class ApixuApiKeyInterceptor @Inject constructor(
        @Named(NetworkModule.APIXU_API_KEY) val apiKey: String
) : ApiKeyInterceptor(
        NetworkModule.APIXU_API_KEY,
        apiKey
)