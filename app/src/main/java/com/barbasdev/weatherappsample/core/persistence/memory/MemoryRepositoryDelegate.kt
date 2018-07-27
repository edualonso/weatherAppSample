package com.barbasdev.weatherappsample.core.persistence.memory

import android.util.Log
import com.barbasdev.weatherappsample.core.network.ApiClient
import com.barbasdev.weatherappsample.core.persistence.Repository
import com.barbasdev.weatherappsample.core.presentation.weather.Weather
import io.reactivex.Observable
import io.reactivex.Single

/**
 *
 */
class MemoryRepositoryDelegate(
        private val apiClient: ApiClient
) : Repository {

    private val weatherCache = mutableListOf<Weather>()

    override fun getWeather(location: String): Single<Weather> {
        return getWeatherFromApiWithSave(location)
                    .startWith(getWeatherFromCache(location))
                    .firstOrError()
    }

    private fun getWeatherFromCache(location: String): Observable<Weather> {
        return Observable.create<Weather> {
            weatherCache
                    .find {
                        it.location.name.contains(location, true)
                    }.apply {
                        if (this != null) {
                            if (System.currentTimeMillis() - lastUpdated < 60000) {
                                Log.e("------------------", "-----------> WEATHER FETCHED FROM THE CACHE")
                                it.onNext(this)
                            } else {
                                Log.e("------------------", "-----------> WEATHER FROM CACHE EXPIRED")
                                weatherCache.remove(this)
                            }
                        }
                        it.onComplete()
                    }
        }
    }

    private fun getWeatherFromApiWithSave(location: String): Observable<Weather> {
        return apiClient
                .getWeather(location)
                .doOnSuccess {
                    weatherCache.add(it)
                    Log.e("------------------", "-----------> WEATHER FETCHED FROM THE API")
                }
                .toObservable()
    }
}