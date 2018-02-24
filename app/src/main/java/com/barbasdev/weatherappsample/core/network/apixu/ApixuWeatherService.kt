package com.barbasdev.weatherappsample.core.network.apixu

import com.barbasdev.weatherappsample.core.network.apixu.dto.ApixuCurrentWeather
import com.barbasdev.weatherappsample.core.network.apixu.dto.ApixuLocation
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *
 */
interface ApixuWeatherService {

    @GET(AUTOCOMPLETION)
    fun getLocation(@Query(QUERY) location: String): Single<List<ApixuLocation>>

    @GET(CURRENT_WEATHER)
    fun getWeather(@Query(QUERY) location: String): Single<ApixuCurrentWeather>

    companion object {
        const val AUTOCOMPLETION = "search.json"
        const val CURRENT_WEATHER = "current.json"
        const val QUERY = "q"

        fun getNameAndCoordinatesForQuery(name: String, lat: Float, lon: Float): String {
            return name + " " + lat.toString() + "," + lon.toString()
        }
    }
}