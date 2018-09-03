package com.barbasdev.weatherappsample.core.persistence.memory

import android.util.Log
import com.barbasdev.weatherappsample.core.network.ApiClient
import com.barbasdev.weatherappsample.core.persistence.Repository
import com.barbasdev.weatherappsample.core.persistence.Repository.Companion.EXPIRATION_TIME
import com.barbasdev.weatherappsample.core.presentation.weather.Weather
import com.barbasdev.weatherappsample.core.presentation.weather.WeatherWrapper
import io.reactivex.Observable
import io.reactivex.Single

/**
 *
 */
class MemoryRepository(
        private val apiClient: ApiClient
) : Repository {

    private val weatherCache = mutableListOf<Weather>()

    override fun getWeather(location: String): Single<Weather> {
        return getWeatherFromApiWithSave(location)
                .startWith(getWeatherFromCache(location))
                .firstOrError()
                .map {
                    it.weather
                }
    }

    override fun getWeatherWrapped(location: String): Single<WeatherWrapper> {
        return getWeatherFromApiWithSave(location)
                .startWith(getWeatherFromCache(location))
                .firstOrError()
    }

    private fun getWeatherFromCache(location: String): Observable<WeatherWrapper> {
        return Observable.create<WeatherWrapper> {
            weatherCache
                    .find {
                        it.location.name.contains(location, true)
                    }.apply {
                        if (this != null) {
                            if (System.currentTimeMillis() - lastUpdated < EXPIRATION_TIME) {
                                Log.e("------------------", "-----------> WEATHER FETCHED FROM THE CACHE")
                                it.onNext(WeatherWrapper(this, "CACHE"))
                            } else {
                                Log.e("------------------", "-----------> WEATHER FROM CACHE EXPIRED")
                                weatherCache.remove(this)
                            }
                        }
                        it.onComplete()
                    }
        }
    }

    private fun getWeatherFromApiWithSave(location: String): Observable<WeatherWrapper> {
        return apiClient
                .getWeather(location)
                .map {
                    WeatherWrapper(it, "API")
                }
                .doOnSuccess {
                    weatherCache.add(it.weather)
                    Log.e("------------------", "-----------> WEATHER FETCHED FROM THE API")
                }
                .toObservable()
    }
}