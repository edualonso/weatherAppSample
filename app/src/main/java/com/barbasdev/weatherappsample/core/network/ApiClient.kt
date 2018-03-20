package com.barbasdev.weatherappsample.core.network

import com.barbasdev.weatherappsample.core.presentation.location.LocationImpl
import com.barbasdev.weatherappsample.core.presentation.weather.WeatherImpl
import io.reactivex.Single

/**
 *
 */
interface ApiClient {
    fun getLocation(location: String): Single<List<LocationImpl>>
    fun getWeather(location: String): Single<WeatherImpl>
}


/**
 *
 */
class ApiClientImpl(
        private val delegate: ApiClient
) : ApiClient by delegate