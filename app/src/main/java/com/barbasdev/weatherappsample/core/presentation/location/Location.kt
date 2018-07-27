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
data class StorableLocation(
        override val id: Long,
        override val name: String,
        override val country: String,
        override val lat: Float,
        override val lon: Float
) : Location