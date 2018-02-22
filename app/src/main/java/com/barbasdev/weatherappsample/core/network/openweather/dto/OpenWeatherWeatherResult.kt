package com.barbasdev.weatherappsample.core.network.openweather.dto

/**
 *
 */
data class OpenWeatherWeatherResult(
        var coord: OpenWeatherCoord,
        val weather: List<OpenWeatherWeather> = arrayListOf(),
        var base: String? = null,
        var main: OpenWeatherMain? = null,
        var wind: OpenWeatherWind? = null,
        var clouds: OpenWeatherClouds? = null,
        var dt: Long? = null,
        var sys: OpenWeatherSys,
        var id: Long,
        var name: String,
        var cod: Long? = null
)