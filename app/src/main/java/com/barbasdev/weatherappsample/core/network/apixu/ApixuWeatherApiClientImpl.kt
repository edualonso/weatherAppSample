package com.barbasdev.weatherappsample.core.network.apixu

import com.barbasdev.weatherappsample.core.network.IWeatherApiClient
import com.barbasdev.weatherappsample.core.presentation.Location
import com.barbasdev.weatherappsample.core.presentation.Weather
import io.reactivex.Single

/**
 *
 */
class ApixuWeatherApiClientImpl(
        private val service: ApixuWeatherService
) : IWeatherApiClient {

    override fun getLocation(location: String): Single<List<Location>> {
        return service
                .getLocation(location)
                .map {
                    it.map {
                        Location(it)
                    }
                }
    }

    override fun getWeather(location: String): Single<Weather> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}