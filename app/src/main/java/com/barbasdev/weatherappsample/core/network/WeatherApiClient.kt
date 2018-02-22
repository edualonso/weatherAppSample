package com.barbasdev.weatherappsample.core.network

import com.barbasdev.weatherappsample.core.presentation.location.Location
import com.barbasdev.weatherappsample.core.presentation.weather.Weather
import io.reactivex.Single

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
class WeatherApiClient(
        private val delegate: IWeatherApiClient
) : IWeatherApiClient by delegate