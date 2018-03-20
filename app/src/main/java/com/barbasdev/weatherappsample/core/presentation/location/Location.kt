package com.barbasdev.weatherappsample.core.presentation.location

/**
 *
 */
interface Location {
    val id: Long
    val name: String
    val country: String
    val lat: Float
    val lon: Float
}

/**
 *
 */
data class LocationImpl(
        private val delegate: Location
) : Location by delegate