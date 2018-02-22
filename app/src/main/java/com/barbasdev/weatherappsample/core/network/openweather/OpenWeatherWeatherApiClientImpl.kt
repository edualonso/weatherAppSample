package com.barbasdev.weatherappsample.core.network.openweather

import com.barbasdev.weatherappsample.core.data.openweather.OpenWeatherLocation
import com.barbasdev.weatherappsample.core.network.IWeatherApiClient
import com.barbasdev.weatherappsample.core.presentation.Location
import com.barbasdev.weatherappsample.core.presentation.Weather
import io.reactivex.Single
import javax.inject.Inject

/**
 *
 */
class OpenWeatherWeatherApiClientImpl @Inject constructor(
        val service: OpenWeatherService
) : IWeatherApiClient {

    override fun getLocation(location: String): Single<List<Location>> {
        return Single.fromCallable {
            mutableListOf<Location>().apply {
                val delegate = OpenWeatherLocation(42,
                        "42-CITY",
                        "42-COUNTRY",
                        42.42F,
                        -42.42F)
                add(Location(delegate))
            }
        }
    }

    override fun getWeather(location: String): Single<Weather> {
        throw RuntimeException("NOT IMPLEMENTED")
    }

}