package com.barbasdev.weatherappsample

import android.os.Bundle
import android.util.Log
import com.barbasdev.weatherappsample.base.BaseActivity
import com.barbasdev.weatherappsample.core.data.openweather.OpenWeatherLocation
import com.barbasdev.weatherappsample.core.network.WeatherApiClient
import com.barbasdev.weatherappsample.core.network.apixu.ApixuWeatherApiClientImpl
import com.barbasdev.weatherappsample.core.network.openweather.OpenWeatherWeatherApiClientImpl
import com.barbasdev.weatherappsample.core.presentation.Location
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject lateinit var apixuDelegate: ApixuWeatherApiClientImpl
    @Inject lateinit var openWeatherDelegate: OpenWeatherWeatherApiClientImpl

    private val apixuWeatherApiClient by lazy { WeatherApiClient(apixuDelegate) }
    private val openWeatherWeatherApiClient by lazy { WeatherApiClient(openWeatherDelegate) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apixuWeatherApiClient
                .getLocation("madrid")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError {
                    Log.e("------", "ERROR -----> ${it.message}")
                }
                .onErrorReturn {
                    val location = Location(
                            OpenWeatherLocation(69,
                                    "69-CITY",
                                    "69-COUNTRY",
                                    69.69F,
                                    -69.69F
                            )
                    )
                    listOf(location)
                }
                .subscribe { results ->
                    results.map {
                        Log.d("------", "-----> ${it.name}")
                    }
                }

        openWeatherWeatherApiClient
                .getLocation("madrid")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError {
                    Log.e("------", "ERROR -----> ${it.message}")
                }
//                .onErrorReturn {
//                    val location = Location(
//                            OpenWeatherLocation(42,
//                                    "42-CITY",
//                                    "42-COUNTRY",
//                                    42.42F,
//                                    -42.42F
//                            )
//                    )
//                    listOf(location)
//                }
                .subscribe { results ->
                    results.map {
                        Log.d("------", "-----> ${it.name}")
                    }
                }
    }
}
