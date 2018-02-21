package com.barbasdev.weatherappsample.core.data.apixu

import com.barbasdev.weatherappsample.core.presentation.ILocation

/**
 *
 */
data class ApixuLocation(
        override val id: Long,
        override val name: String,
        override val country: String,
        override val lat: Float,
        override val lon: Float,

        // Apixu-specific fields
        val region: String = "",
        val url: String = ""
) : ILocation