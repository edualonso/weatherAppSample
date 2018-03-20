package com.barbasdev.weatherappsample.core.persistence.memory

import android.util.Log
import com.barbasdev.weatherappsample.core.network.ApiClientImpl
import com.barbasdev.weatherappsample.core.persistence.Repository
import com.barbasdev.weatherappsample.core.presentation.weather.WeatherImpl
import io.reactivex.Observable
import io.reactivex.Single

/**
 *
 */
class MemoryRepositoryDelegate(
        private val apiClient: ApiClientImpl
) : Repository {

    private val weatherCache = mutableListOf<WeatherImpl>()

    override fun getWeather(location: String): Single<WeatherImpl> {
        return getWeatherFromApi(location)
                    .startWith(getWeatherFromCache(location))
                    .firstOrError()
    }

    private fun getWeatherFromCache(location: String): Observable<WeatherImpl> {
        return Observable.create<WeatherImpl> {
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

    private fun getWeatherFromApi(location: String): Observable<WeatherImpl> {
        return apiClient
                .getWeather(location)
                .doOnSuccess {
                    weatherCache.add(it)
                    Log.e("------------------", "-----------> WEATHER FETCHED FROM THE API")
                }
                .toObservable()
    }
}