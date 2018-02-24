package com.barbasdev.weatherappsample.core.network.openweather.dto

/**
 *
 */
open class OpenWeatherLocation(
        val id: Long = 0,
        val name: String = "",
        val country: String = "",
        val coord: OpenWeatherCoord = OpenWeatherCoord(0F,0F)
)