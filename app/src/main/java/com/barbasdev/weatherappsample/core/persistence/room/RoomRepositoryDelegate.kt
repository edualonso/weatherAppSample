package com.barbasdev.weatherappsample.core.persistence.room

import android.util.Log
import com.barbasdev.weatherappsample.core.network.ApiClient
import com.barbasdev.weatherappsample.core.persistence.Repository
import com.barbasdev.weatherappsample.core.persistence.Repository.Companion.EXPIRATION_TIME
import com.barbasdev.weatherappsample.core.presentation.weather.Weather
import io.reactivex.Observable
import io.reactivex.Single

class RoomRepositoryDelegate(
        private val apiClient: ApiClient,
        private val weatherDao: WeatherDao
) : Repository {

    override fun getWeather(location: String): Single<Weather> {
        return getWeatherFromApiWithSave(location)
                .startWith(getWeatherFromRoom(location))
                .firstOrError()
    }

    private fun getWeatherFromRoom(location: String): Observable<WeatherRoomDelegate> {
        return Observable.create {
            weatherDao.getWeather(location)
                    .run {
                        if (this != null) {
                            if (System.currentTimeMillis() - lastUpdated < EXPIRATION_TIME) {
                                Log.e("------------------", "-----------> WEATHER FETCHED FROM THE CACHE")
                                it.onNext(this)
                            } else {
                                Log.e("------------------", "-----------> WEATHER FROM CACHE EXPIRED")
                                weatherDao.deleteWeather(location)
                            }
                        }
                        it.onComplete()
                    }
        }
    }

    private fun getWeatherFromApiWithSave(location: String): Observable<Weather> {
        return apiClient.getWeather(location)
                .doOnSuccess {
                    Log.e("------------------", "-----------> ${Thread.currentThread().name} - WEATHER FETCHED FROM THE API: SAVING IN ROOM...")
                    weatherDao.saveWeather(it.toRoom())
                    Log.e("------------------", "-----------> SAVED!!!")
                }
                .toObservable()
    }

    private fun Weather.toRoom(): WeatherRoomDelegate {
        with(location) {
            return WeatherRoomDelegate(
                    lastUpdated,
                    temperature,
                    id,
                    name,
                    country,
                    lat,
                    lon)
        }
    }
}