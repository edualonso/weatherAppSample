package com.barbasdev.weatherappsample.core.presentation.weather.delegate

import com.barbasdev.weatherappsample.core.network.apixu.dto.ApixuCurrentWeather
import com.barbasdev.weatherappsample.core.presentation.location.LocationImpl
import com.barbasdev.weatherappsample.core.presentation.location.delegate.ApixuLocationDelegate
import com.barbasdev.weatherappsample.core.presentation.weather.Weather

/**
 *
 */
data class ApixuWeatherDelegate(
        private val weather: ApixuCurrentWeather
) : Weather {

    private val syncTime = System.currentTimeMillis()

    override val lastUpdated: Long
        get() = syncTime
    override val temperature: Float
        get() = weather.current.tempC?.toFloat() ?: -666F
    override val location: LocationImpl
        get() = LocationImpl(ApixuLocationDelegate(weather.location))

}