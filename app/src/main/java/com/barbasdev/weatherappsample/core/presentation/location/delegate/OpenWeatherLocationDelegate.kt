package com.barbasdev.weatherappsample.core.presentation.location.delegate

import com.barbasdev.weatherappsample.core.network.openweather.dto.OpenWeatherLocation
import com.barbasdev.weatherappsample.core.presentation.location.Location

/**
 * Created by edu on 23/02/2018.
 */
data class OpenWeatherLocationDelegate(
        private val location: OpenWeatherLocation
) : Location {

    override val id: Long
        get() = location.id
    override val name: String
        get() = location.name
    override val country: String
        get() = location.country
    override val lat: Float
        get() = location.coord.lat ?: 0F
    override val lon: Float
        get() = location.coord.lon ?: 0F

}