package com.barbasdev.weatherappsample.core.network.apixu.dto

/**
 *
 */
data class ApixuCurrentWeather(
        val location: ApixuLocation,
        val current: ApixuCurrent
)