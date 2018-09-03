package com.barbasdev.weatherappsample.core.persistence.realm

import com.barbasdev.weatherappsample.core.presentation.location.StorableLocation
import com.barbasdev.weatherappsample.core.presentation.weather.Weather
import io.realm.RealmObject
import io.realm.annotations.Ignore
import io.realm.annotations.PrimaryKey

/**
 *
 */
open class WeatherRealm(
        override var lastUpdated: Long = 0,
        override var temperature: Float = 0f,
        @PrimaryKey var id: Long = 0,
        var name: String = "",
        var country: String = "",
        var lat: Float = 0f,
        var lon: Float = 0f
) : Weather, RealmObject() {

    @Ignore
    override var location: StorableLocation = StorableLocation()
        get() = StorableLocation(id, name, country, lat, lon)
}