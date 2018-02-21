package com.barbasdev.weatherappsample.core.network

import com.barbasdev.weatherappsample.core.presentation.Location
import com.barbasdev.weatherappsample.core.presentation.Weather
import io.reactivex.Single
import javax.inject.Inject

/**
 *
 */
interface IWeatherApiClient {

    fun getLocation(location: String): Single<List<Location>>
    fun getWeather(location: String): Single<Weather>

}

/**
 *
 */
class WeatherApiClient @Inject constructor(
        private val delegate: IWeatherApiClient
) : IWeatherApiClient by delegate