package com.barbasdev.weatherappsample.core.network.apixu.dto

import com.google.gson.annotations.SerializedName

/**
 *
 */
data class ApixuLocation(
        val id: Long? = null,
        val name: String = "",
        val region: String = "",
        val country: String = "",
        val lat: Float = 0.0F,
        val lon: Float = 0.0F,
        val url: String? = null,
        val localtime: String? = null,
        @SerializedName("tz_id") val tzId: String? = null,
        @SerializedName("localtime_epoch") val localtimeEpoch: Long? = null
)