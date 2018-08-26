package com.barbasdev.weatherappsample.core.persistence.room

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.barbasdev.weatherappsample.core.presentation.location.Location
import com.barbasdev.weatherappsample.core.presentation.location.StorableLocation
import com.barbasdev.weatherappsample.core.presentation.weather.Weather
import io.realm.RealmObject

@Entity(tableName = "weather")
data class WeatherRoom(
        override val lastUpdated: Long,
        override val temperature: Float,
        @PrimaryKey val id: Long,
        val name: String,
        val country: String,
        val lat: Float,
        val lon: Float
) : Weather {

    override val location: Location
        get() = StorableLocation(id, name, country, lat, lon)
}
