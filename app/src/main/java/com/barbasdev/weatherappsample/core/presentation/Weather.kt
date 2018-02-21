package com.barbasdev.weatherappsample.core.presentation

/**
 *
 */
interface IWeather {
    val lastUpdated: Long
    val location: Location

    companion object {
        const val NO_COORDS_VALUE = -1F
    }
}

/**
 *
 */
data class Weather(
        val delegate: IWeather
) : IWeather by delegate