package com.barbasdev.weatherappsample.core.network.openweather

import com.barbasdev.weatherappsample.core.network.ApiClient
import com.barbasdev.weatherappsample.core.network.openweather.dto.OpenWeatherCoord
import com.barbasdev.weatherappsample.core.network.openweather.dto.OpenWeatherLocation
import com.barbasdev.weatherappsample.core.presentation.location.Location
import com.barbasdev.weatherappsample.core.presentation.location.delegate.OpenWeatherLocationDelegate
import com.barbasdev.weatherappsample.core.presentation.weather.Weather
import com.barbasdev.weatherappsample.core.presentation.weather.delegate.OpenWeatherWeatherDelegate
import io.reactivex.Single
import javax.inject.Inject

/**
 *
 */
class OpenWeatherApiClientDelegate @Inject constructor(
        private val service: OpenWeatherService
) : ApiClient {

    override fun getLocation(location: String): Single<List<Location>> {
        return Single
                .fromCallable {
                    listOf<OpenWeatherLocationDelegate>()
                            .plus(OpenWeatherLocationDelegate(
                                    OpenWeatherLocation(42,
                                            "42-CITY",
                                            "42-COUNTRY",
                                            OpenWeatherCoord(42.42F, -42.42F)
                            )))
                }
    }

    override fun getWeather(location: String): Single<Weather> {
        return service
                .getWeather(location)
                .map {
                    OpenWeatherWeatherDelegate(it)
                }
    }

}