package com.barbasdev.weatherappsample.di.modules

import com.barbasdev.weatherappsample.BuildConfig
import com.barbasdev.weatherappsample.core.network.ApiKeyInterceptor
import com.barbasdev.weatherappsample.core.network.apixu.ApixuApiKeyInterceptor
import com.barbasdev.weatherappsample.core.network.apixu.ApixuWeatherService
import com.barbasdev.weatherappsample.core.network.openweather.OpenWeatherApiKeyInterceptor
import com.barbasdev.weatherappsample.core.network.openweather.OpenWeatherService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
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
            @Named(APIXU_BASE_URL) apixuBaseUrl: String,
            apixuApiKeyInterceptor: ApixuApiKeyInterceptor
    ): ApixuWeatherService {
        return getRetrofit(apixuBaseUrl, apixuApiKeyInterceptor)
                .create(ApixuWeatherService::class.java)
    }

    @Provides
    @Singleton
    fun providesOpenWeatherService(
            @Named(APIXU_BASE_URL) openWeatherBaseUrl: String,
            openWeatherApiKeyInterceptor: OpenWeatherApiKeyInterceptor
    ): OpenWeatherService {
        return getRetrofit(openWeatherBaseUrl, openWeatherApiKeyInterceptor)
                .create(OpenWeatherService::class.java)
    }


    //--------------------------------------------------------------------------------
    // Private stuff
    //--------------------------------------------------------------------------------

    private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        when {
            BuildConfig.DEBUG -> loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            else -> loggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        return loggingInterceptor
    }

    private fun <T : ApiKeyInterceptor> getRetrofit(
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

    companion object {
        const val APIXU_BASE_URL = "APIXU_BASE_URL"
        const val APIXU_API_KEY = "key"
        const val APIXU_API_CLIENT = "apixu"

        const val OPENWEATHER_BASE_URL = "OPENWEATHER_BASE_URL"
        const val OPENWEATHER_API_KEY = "appid"
        const val OPENWEATHER_API_CLIENT = "openweather"

    }
}