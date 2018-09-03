package com.barbasdev.weatherappsample.core.presentation.location.delegate

import com.barbasdev.weatherappsample.core.network.apixu.dto.ApixuLocation
import com.barbasdev.weatherappsample.core.presentation.location.Location

/**
 * Created by edu on 23/02/2018.
 */
data class ApixuLocation(
        private val location: ApixuLocation
) : Location {

    override val id: Long
        get() = location.id ?: 0
    override val name: String
        get() = location.name
    override val country: String
        get() = location.country
    override val lat: Float
        get() = location.lat
    override val lon: Float
        get() = location.lon

}