package com.barbasdev.weatherappsample.core.presentation.location.delegate

import com.barbasdev.weatherappsample.core.network.openweather.dto.OpenWeatherLocationDto
import com.barbasdev.weatherappsample.core.presentation.location.Location

/**
 * Created by edu on 23/02/2018.
 */
data class OpenWeatherLocation(
        private val locationDto: OpenWeatherLocationDto
) : Location {

    override val id: Long
        get() = locationDto.id
    override val name: String
        get() = locationDto.name
    override val country: String
        get() = locationDto.country
    override val lat: Float
        get() = locationDto.coord.lat ?: 0F
    override val lon: Float
        get() = locationDto.coord.lon ?: 0F

}