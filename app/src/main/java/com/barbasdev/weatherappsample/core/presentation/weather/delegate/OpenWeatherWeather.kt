package com.barbasdev.weatherappsample.core.presentation.weather.delegate

import com.barbasdev.weatherappsample.core.network.openweather.dto.OpenWeatherCoord
import com.barbasdev.weatherappsample.core.network.openweather.dto.OpenWeatherLocationDto
import com.barbasdev.weatherappsample.core.network.openweather.dto.OpenWeatherWeatherResult
import com.barbasdev.weatherappsample.core.presentation.location.Location
import com.barbasdev.weatherappsample.core.presentation.location.delegate.OpenWeatherLocation
import com.barbasdev.weatherappsample.core.presentation.weather.Weather

/**
 *
 */
data class OpenWeatherWeather(
        private val weather: OpenWeatherWeatherResult
) : Weather {

    private val syncTime = System.currentTimeMillis()

    override val lastUpdated: Long
        get() = syncTime
    override val temperature: Float
        get() = weather.main?.temp ?: -666F
    override val location: Location
        get() {
            with(weather) {
                val openWeatherLocation = OpenWeatherLocationDto(
                        id,
                        name,
                        sys.country,
                        OpenWeatherCoord(coord.lat ?: 0F, coord.lon ?: 0F)
                )
                return OpenWeatherLocation(openWeatherLocation)
            }
        }

}