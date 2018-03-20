package com.barbasdev.weatherappsample.core.presentation.weather.delegate

import com.barbasdev.weatherappsample.core.network.openweather.dto.OpenWeatherCoord
import com.barbasdev.weatherappsample.core.network.openweather.dto.OpenWeatherLocation
import com.barbasdev.weatherappsample.core.network.openweather.dto.OpenWeatherWeatherResult
import com.barbasdev.weatherappsample.core.presentation.location.LocationImpl
import com.barbasdev.weatherappsample.core.presentation.location.delegate.OpenWeatherLocationDelegate
import com.barbasdev.weatherappsample.core.presentation.weather.Weather

/**
 *
 */
data class OpenWeatherWeatherDelegate(
        private val weather: OpenWeatherWeatherResult
) : Weather {

    private val syncTime = System.currentTimeMillis()

    override val lastUpdated: Long
        get() = syncTime
    override val location: LocationImpl
        get() {
            with(weather) {
                val openWeatherLocation = OpenWeatherLocation(
                        id,
                        name,
                        sys.country,
                        OpenWeatherCoord(coord.lat ?: 0F, coord.lon ?: 0F)
                )
                return LocationImpl(OpenWeatherLocationDelegate(openWeatherLocation))
            }
        }

}