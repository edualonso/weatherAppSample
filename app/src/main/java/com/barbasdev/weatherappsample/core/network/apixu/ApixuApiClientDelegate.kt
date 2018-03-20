package com.barbasdev.weatherappsample.core.network.apixu

import com.barbasdev.weatherappsample.core.network.ApiClient
import com.barbasdev.weatherappsample.core.presentation.location.LocationImpl
import com.barbasdev.weatherappsample.core.presentation.location.delegate.ApixuLocationDelegate
import com.barbasdev.weatherappsample.core.presentation.weather.WeatherImpl
import com.barbasdev.weatherappsample.core.presentation.weather.delegate.ApixuWeatherDelegate
import io.reactivex.Single
import javax.inject.Inject

/**
 *
 */
class ApixuApiClientDelegate @Inject constructor(
        private val service: ApixuWeatherService
) : ApiClient {

    override fun getLocation(location: String): Single<List<LocationImpl>> {
        return service
                .getLocation(location)
                .map {
                    it.map {
                        LocationImpl(ApixuLocationDelegate(it))
                    }
                }
    }

    override fun getWeather(location: String): Single<WeatherImpl> {
        return service.getWeather(location)
                .map {
                    WeatherImpl(ApixuWeatherDelegate(it))
                }
    }

}