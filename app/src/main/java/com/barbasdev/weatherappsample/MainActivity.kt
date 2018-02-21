package com.barbasdev.weatherappsample

import android.os.Bundle
import android.util.Log
import com.barbasdev.weatherappsample.base.BaseActivity
import com.barbasdev.weatherappsample.core.network.WeatherApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var weatherApiClient: WeatherApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        weatherApiClient
                .getLocation("madrid")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { results ->
                    results.map {
                        Log.d("------", "-----> ${it.name}")
                    }
                }
    }
}
