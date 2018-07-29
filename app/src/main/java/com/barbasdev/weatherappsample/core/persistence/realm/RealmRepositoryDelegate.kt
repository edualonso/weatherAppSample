package com.barbasdev.weatherappsample.core.persistence.realm

import android.arch.persistence.room.PrimaryKey
import android.util.Log
import com.barbasdev.weatherappsample.core.network.ApiClient
import com.barbasdev.weatherappsample.core.persistence.Repository
import com.barbasdev.weatherappsample.core.persistence.Repository.Companion.EXPIRATION_TIME
import com.barbasdev.weatherappsample.core.presentation.location.StorableLocation
import com.barbasdev.weatherappsample.core.presentation.weather.Weather
import io.reactivex.Observable
import io.reactivex.Single
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.Ignore

class RealmRepositoryDelegate(
        private val apiClient: ApiClient,
        private val realmConfiguration: RealmConfiguration
) : Repository {

    override fun getWeather(location: String): Single<Weather> {
        return getWeatherFromApiWithSave(location)
                .startWith(getWeatherFromRealm(location))
                .firstOrError()
    }

    private fun getWeatherFromRealm(location: String): Observable<WeatherRealmDelegate> {
        return Observable.create {

            Realm.getInstance(realmConfiguration)?.apply {
                val results: RealmResults<WeatherRealmDelegate> = where(WeatherRealmDelegate::class.java)
                    .equalTo("name", location)
                    .findAll()
                if (results.size > 0) {
                    results.first()?.let { weather ->
                        val now = System.currentTimeMillis()
                        if (now - weather.lastUpdated < EXPIRATION_TIME) {
                            Log.e("------------------", "-----------> ${Thread.currentThread().name} - WEATHER FETCHED FROM THE CACHE")
                            it.onNext(copyFromRealm(weather))
                        } else {
                            Log.e("------------------", "-----------> WEATHER FROM CACHE EXPIRED")
                            beginTransaction()
                            weather.deleteFromRealm()
                            commitTransaction()
                        }
                    }
                }
                it.onComplete()
            }
        }
    }

    private fun getWeatherFromApiWithSave(location: String): Observable<Weather> {
        return apiClient.getWeather(location)
                .doOnSuccess { weather ->
                    with(Realm.getInstance(realmConfiguration)) {
                        executeTransaction {
                            Log.e("------------------", "-----------> ${Thread.currentThread().name} - WEATHER FETCHED FROM THE API: SAVING IN ROOM...")
                            it.insertOrUpdate(weather.toRealm())
                            Log.e("------------------", "-----------> SAVED!!!")
                        }
                    }
                }
                .toObservable()
    }

    private fun Weather.toRealm(): WeatherRealmDelegate {
        with(location) {
            return WeatherRealmDelegate(
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


/**
 *
 */
open class WeatherRealmDelegate(
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
