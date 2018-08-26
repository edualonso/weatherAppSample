package com.barbasdev.weatherappsample.core.persistence.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather WHERE name = :location")
    fun getWeather(location: String): WeatherRoom?

    @Insert
    fun saveWeather(weather: WeatherRoom)

    @Delete
    fun deleteWeather(weather: WeatherRoom)

    @Query("DELETE FROM weather WHERE name = :location")
    fun deleteWeather(location: String)
}
