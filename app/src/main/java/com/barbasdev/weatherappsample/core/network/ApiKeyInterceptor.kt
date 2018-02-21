package com.barbasdev.weatherappsample.core.network

import okhttp3.Interceptor
import okhttp3.Response

/**
 *
 */
abstract class ApiKeyInterceptor(
        val apiKeyKey: String,
        val apiKeyValue: String
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url()

        val url = originalHttpUrl.newBuilder()
                .addQueryParameter(apiKeyKey, apiKeyValue)
                .build()

        // Request customization: add request headers
        val requestBuilder = original.newBuilder()
                .url(url)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }

}