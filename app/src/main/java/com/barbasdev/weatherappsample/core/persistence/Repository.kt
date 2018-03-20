package com.barbasdev.weatherappsample.core.persistence

import com.barbasdev.weatherappsample.core.presentation.weather.WeatherImpl
import io.reactivex.Single

/**
 *
 */
interface Repository {
    fun getWeather(location: String): Single<WeatherImpl>
}


/**
 *
 */
class RepositoryImpl(
        private val delegate: Repository
) : Repository by delegate