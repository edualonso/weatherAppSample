package com.barbasdev.weatherappsample.core.data.openweather

import com.barbasdev.weatherappsample.core.presentation.ILocation

/**
 *
 */
open class OpenWeatherLocation(
        override var id: Long = 0,
        override var name: String = "",
        override var country: String = "",
        override val lat: Float,
        override val lon: Float
) : ILocation {

    private val coord: OpenWeatherCoord?
        get() = OpenWeatherCoord(lat, lon)
}