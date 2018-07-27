package com.barbasdev.weatherappsample.core.presentation.weather

import com.barbasdev.weatherappsample.core.presentation.location.Location

/**
 *
 */
interface Weather {
    val lastUpdated: Long
    val temperature: Float
    val location: Location

    companion object {
        const val NO_COORDS_VALUE = -1F
    }
}
