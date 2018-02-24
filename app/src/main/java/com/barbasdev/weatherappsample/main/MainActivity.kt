package com.barbasdev.weatherappsample.main

import android.os.Bundle
import android.util.Log
import com.barbasdev.weatherappsample.R
import com.barbasdev.weatherappsample.base.BaseActivity
import com.barbasdev.weatherappsample.core.network.ApiClient
import com.barbasdev.weatherappsample.core.network.openweather.dto.OpenWeatherCoord
import com.barbasdev.weatherappsample.core.network.openweather.dto.OpenWeatherLocation
import com.barbasdev.weatherappsample.core.presentation.location.Location
import com.barbasdev.weatherappsample.core.presentation.location.delegate.OpenWeatherLocationDelegate
import com.barbasdev.weatherappsample.di.modules.NetworkModule
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Named

class MainActivity : BaseActivity() {

    @Inject
    @field:Named(NetworkModule.APIXU_API_CLIENT)
    lateinit var apixuApiClient: ApiClient

    @Inject
    @field:Named(NetworkModule.OPENWEATHER_API_CLIENT)
    lateinit var openWeatherApiClient: ApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchLocation(apixuApiClient)
        fetchLocation(openWeatherApiClient)

        fetchWeather(apixuApiClient)
        fetchWeather(openWeatherApiClient)
    }

    private fun fetchLocation(apiClient: ApiClient) {
        apiClient
                .getLocation("madrid")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError {
                    Log.e("APIXU ---------", "-----------> ERROR: ${it.message}")
                }
                .onErrorReturn {
                    val locationDto = OpenWeatherLocation(69,
                            "69-CITY",
                            "69-COUNTRY",
                            OpenWeatherCoord(69.69F, -69.69F)
                    )
                    val location = Location(OpenWeatherLocationDelegate(locationDto))
                    listOf(location)
                }
                .subscribe { results ->
                    results.map {
                        Log.d("APIXU ---------", "-----------> LOCATIONS: ${it.name}")
                    }
                }
    }

    private fun fetchWeather(apiClient: ApiClient) {
        apiClient
                .getWeather("madrid")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { weather ->
                    Log.d("APIXU ---------", "-----------> WEATHER: ${weather.location.name} updated at ${weather.lastUpdated}")
                }
    }
}
