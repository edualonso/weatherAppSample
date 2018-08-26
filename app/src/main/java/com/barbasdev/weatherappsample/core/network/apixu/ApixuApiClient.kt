package com.barbasdev.weatherappsample.core.network.apixu

import com.barbasdev.weatherappsample.core.network.ApiClient
import com.barbasdev.weatherappsample.core.presentation.location.Location
import com.barbasdev.weatherappsample.core.presentation.location.delegate.ApixuLocation
import com.barbasdev.weatherappsample.core.presentation.weather.Weather
import com.barbasdev.weatherappsample.core.presentation.weather.delegate.ApixuWeather
import io.reactivex.Single
import javax.inject.Inject

/**
 *
 */
class ApixuApiClient @Inject constructor(
        private val service: ApixuWeatherService
) : ApiClient {

    override fun getLocation(location: String): Single<List<Location>> {
        return service
                .getLocation(location)
                .map {
                    it.map {
                        ApixuLocation(it)
                    }
                }
    }

    override fun getWeather(location: String): Single<Weather> {
        return service.getWeather(location)
                .map {
                    ApixuWeather(it)
                }
    }

}