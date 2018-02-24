package com.barbasdev.weatherappsample.core.network.apixu

import com.barbasdev.weatherappsample.core.network.IApiClient
import com.barbasdev.weatherappsample.core.presentation.location.Location
import com.barbasdev.weatherappsample.core.presentation.location.delegate.ApixuLocationDelegate
import com.barbasdev.weatherappsample.core.presentation.weather.Weather
import com.barbasdev.weatherappsample.core.presentation.weather.delegate.ApixuWeatherDelegate
import io.reactivex.Single
import javax.inject.Inject

/**
 *
 */
class ApixuApiClientDelegate @Inject constructor(
        private val service: ApixuWeatherService
) : IApiClient {

    override fun getLocation(location: String): Single<List<Location>> {
        return service
                .getLocation(location)
                .map {
                    it.map {
                        Location(ApixuLocationDelegate(it))
                    }
                }
    }

    override fun getWeather(location: String): Single<Weather> {
        return service.getWeather(location)
                .map {
                    Weather(ApixuWeatherDelegate(it))
                }
    }

}