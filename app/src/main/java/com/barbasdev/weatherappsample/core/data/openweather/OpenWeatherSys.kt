package com.barbasdev.weatherappsample.core.data.openweather

/**
 *
 */
data class OpenWeatherSys(
        var message: Float,
        var country: String,
        var sunrise: Long,
        var sunset: Long
)