package com.barbasdev.weatherappsample.core.presentation.weather

import com.barbasdev.weatherappsample.core.presentation.location.Location

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
        private val delegate: IWeather
) : IWeather by delegate