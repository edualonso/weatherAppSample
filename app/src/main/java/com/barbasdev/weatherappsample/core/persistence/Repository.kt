package com.barbasdev.weatherappsample.core.persistence

import com.barbasdev.weatherappsample.core.presentation.weather.Weather
import io.reactivex.Single

/**
 *
 */
interface IRepository {
    fun getWeather(location: String): Single<Weather>
}


/**
 *
 */
class Repository(
        private val delegate: IRepository
) : IRepository by delegate