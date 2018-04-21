package com.barbasdev.weatherappsample.core.network

import com.barbasdev.weatherappsample.core.presentation.location.Location
import com.barbasdev.weatherappsample.core.presentation.weather.Weather
import io.reactivex.Single

/**
 *
 */
interface ApiClient {
    fun getLocation(location: String): Single<List<Location>>
    fun getWeather(location: String): Single<Weather>
}


/**
 *
 */
class ApiClientImpl(
        private val delegate: ApiClient
) : ApiClient by delegate