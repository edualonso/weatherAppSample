package com.barbasdev.weatherappsample.core.persistence

import com.barbasdev.weatherappsample.core.presentation.weather.Weather
import io.reactivex.Single

/**
 *
 */
interface Repository {
    fun getWeather(location: String): Single<Weather>
}


/**
 *
 */
class RepositoryImpl(
        private val delegate: Repository
) : Repository by delegate