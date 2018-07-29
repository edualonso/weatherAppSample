package com.barbasdev.weatherappsample.core.presentation.location

import io.realm.RealmObject

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
open class StorableLocation(
        override var id: Long = 0,
        override var name: String = "",
        override var country: String = "",
        override var lat: Float = 0f,
        override var lon: Float = 0f
) : Location, RealmObject()