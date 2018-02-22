package com.barbasdev.weatherappsample.core.presentation.weather.delegate

import com.barbasdev.weatherappsample.core.network.apixu.dto.ApixuCurrentWeather
import com.barbasdev.weatherappsample.core.presentation.location.Location
import com.barbasdev.weatherappsample.core.presentation.location.delegate.ApixuLocationDelegate
import com.barbasdev.weatherappsample.core.presentation.weather.IWeather

/**
 *
 */
data class ApixuWeatherDelegate(
        private val weather: ApixuCurrentWeather
) : IWeather {

    override val lastUpdated: Long
        get() = weather.current.lastUpdatedEpoch ?: System.currentTimeMillis()
    override val location: Location
        get() = Location(ApixuLocationDelegate(weather.location))

}