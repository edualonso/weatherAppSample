package com.barbasdev.weatherappsample.core.persistence

import com.barbasdev.weatherappsample.core.presentation.weather.Weather
import com.barbasdev.weatherappsample.core.presentation.weather.WeatherWrapper
import io.reactivex.Single

/**
 *
 */
interface Repository {
    fun getWeather(location: String): Single<Weather>
    fun getWeatherWrapped(location: String): Single<WeatherWrapper>


    companion object {
        const val EXPIRATION_TIME = 10000
    }
}
