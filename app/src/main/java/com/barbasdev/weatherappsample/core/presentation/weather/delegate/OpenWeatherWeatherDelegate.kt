package com.barbasdev.weatherappsample.core.presentation.weather.delegate

import com.barbasdev.weatherappsample.core.network.openweather.dto.OpenWeatherCoord
import com.barbasdev.weatherappsample.core.network.openweather.dto.OpenWeatherLocation
import com.barbasdev.weatherappsample.core.network.openweather.dto.OpenWeatherWeatherResult
import com.barbasdev.weatherappsample.core.presentation.location.Location
import com.barbasdev.weatherappsample.core.presentation.location.delegate.OpenWeatherLocationDelegate
import com.barbasdev.weatherappsample.core.presentation.weather.IWeather

/**
 *
 */
data class OpenWeatherWeatherDelegate(
        private val weather: OpenWeatherWeatherResult
) : IWeather {

    override val lastUpdated: Long
        get() = weather.dt ?: System.currentTimeMillis()
    override val location: Location
        get() {
            with(weather) {
                val openWeatherLocation = OpenWeatherLocation(
                        id,
                        name,
                        sys.country,
                        OpenWeatherCoord(coord.lat ?: 0F, coord.lon ?: 0F)
                )
                return Location(OpenWeatherLocationDelegate(openWeatherLocation))
            }
        }

}