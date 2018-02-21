package com.barbasdev.weatherappsample.core.network.openweather

import com.barbasdev.weatherappsample.core.data.openweather.OpenWeatherWeatherResult
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *
 */
interface OpenWeatherService {

    @GET(WEATHER)
    fun getWeather(@Query(QUERY) query: String): Single<OpenWeatherWeatherResult>

    companion object {
        const val WEATHER = "weather"
        const val QUERY = "q"
    }
}