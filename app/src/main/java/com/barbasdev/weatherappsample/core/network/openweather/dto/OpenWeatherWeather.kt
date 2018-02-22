package com.barbasdev.weatherappsample.core.network.openweather.dto

/**
 *
 */
data class OpenWeatherWeather(
        var id: Long? = null,
        var main: String? = null,
        var description: String? = null,
        var icon: String? = null
)