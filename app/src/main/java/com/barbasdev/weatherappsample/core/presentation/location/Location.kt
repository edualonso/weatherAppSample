package com.barbasdev.weatherappsample.core.presentation.location

/**
 *
 */
interface ILocation {
    val id: Long
    val name: String
    val country: String
    val lat: Float
    val lon: Float
}

/**
 *
 */
data class Location(
        private val delegate: ILocation
) : ILocation by delegate