package com.barbasdev.weatherappsample.core.presentation.weather.delegate

import com.barbasdev.weatherappsample.core.network.apixu.dto.ApixuCurrentWeather
import com.barbasdev.weatherappsample.core.presentation.location.Location
import com.barbasdev.weatherappsample.core.presentation.location.delegate.ApixuLocation
import com.barbasdev.weatherappsample.core.presentation.weather.Weather

/**
 *
 */
data class ApixuWeather(
        private val weather: ApixuCurrentWeather
) : Weather {

    private val syncTime = System.currentTimeMillis()

    override val lastUpdated: Long
        get() = syncTime
    override val temperature: Float
        get() = weather.current.tempC?.toFloat() ?: -666F
    override val location: Location
        get() = ApixuLocation(weather.location)

}