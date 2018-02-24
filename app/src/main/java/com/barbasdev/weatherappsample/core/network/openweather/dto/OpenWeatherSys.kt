package com.barbasdev.weatherappsample.core.network.openweather.dto

/**
 *
 */
data class OpenWeatherSys(
        var message: Float,
        var country: String,
        var sunrise: Long,
        var sunset: Long
)