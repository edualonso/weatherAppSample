package com.barbasdev.weatherappsample.core.presentation.weather

import com.barbasdev.weatherappsample.core.presentation.location.LocationImpl

/**
 *
 */
interface Weather {
    val lastUpdated: Long
    val location: LocationImpl

    companion object {
        const val NO_COORDS_VALUE = -1F
    }
}

/**
 *
 */
data class WeatherImpl(
        private val delegate: Weather
) : Weather by delegate