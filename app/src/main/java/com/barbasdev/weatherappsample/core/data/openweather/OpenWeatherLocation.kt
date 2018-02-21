package com.barbasdev.weatherappsample.core.data.openweather

/**
 *
 */
open class OpenWeatherLocation(
    var id: Long = 0,
    var name: String = "",
    var country: String = "",
    var coord: OpenWeatherCoord? = null
)