package com.barbasdev.weatherappsample.di

import com.barbasdev.weatherappsample.BuildConfig
import com.barbasdev.weatherappsample.core.network.ApiKeyInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetworkConstants {
    const val CONST_APIXU_BASE_URL = "http://api.apixu.com/v1/"
    const val CONST_APIXU_API_KEY = "11682c59698444f6b59160534171912"
    const val CONST_OPENWEATHER_BASE_URL = "http://api.openweathermap.org/data/2.5/"
    const val CONST_OPENWEATHER_API_KEY = "75805b09ea06260c9eb71391b785f444"

    const val APIXU_BASE_URL = "APIXU_BASE_URL"
    const val APIXU_API_KEY = "key"
    const val APIXU_API_CLIENT = "apixu"

    const val OPENWEATHER_BASE_URL = "OPENWEATHER_BASE_URL"
    const val OPENWEATHER_API_KEY = "appid"
    const val OPENWEATHER_API_CLIENT = "openweather"

    private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        when {
            BuildConfig.DEBUG -> loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            else -> loggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        return loggingInterceptor
    }

    fun <T : ApiKeyInterceptor> getRetrofit(
            baseUrl: String,
            apiKeyInterceptor: T
    ): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClient.Builder()
                        .addInterceptor(getHttpLoggingInterceptor())
                        .addInterceptor(apiKeyInterceptor)
                        .build())
                .build()
    }
}