package com.barbasdev.weatherappsample.core.persistence.room

import com.barbasdev.weatherappsample.core.network.ApiClientImpl
import com.barbasdev.weatherappsample.core.persistence.Repository
import com.barbasdev.weatherappsample.core.presentation.weather.Weather
import io.reactivex.Observable
import io.reactivex.Single

class RoomRepositoryDelegate(
        private val apiClient: ApiClientImpl
) : Repository {

    override fun getWeather(location: String): Single<Weather> {
        TODO()
    }

    private fun getWeatherFromApiWithSave(location: String): Observable<Weather> {
        return apiClient
                .getWeather(location)
                .doOnSuccess {

                }
                .toObservable()
    }
}