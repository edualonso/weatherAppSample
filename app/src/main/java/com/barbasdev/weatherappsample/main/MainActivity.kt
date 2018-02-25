package com.barbasdev.weatherappsample.main

import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.barbasdev.weatherappsample.R
import com.barbasdev.weatherappsample.base.BaseActivity
import com.barbasdev.weatherappsample.core.persistence.Repository
import com.barbasdev.weatherappsample.di.modules.RepositoryModule
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Named

class MainActivity : BaseActivity() {

    @Inject
    @field:Named(RepositoryModule.REPOSITORY_MEMORY_APIXU)
    lateinit var memoryRepositoryApixu: Repository

//    @Inject
//    @field:Named(RepositoryModule.REPOSITORY_MEMORY_OPENWEATHER)
//    lateinit var memoryRepositoryOpenWeather: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button).setOnClickListener {
            Log.e("------------------", "-----------> FETCHING WEATHER")
            fetchWeather()
        }
    }

    private fun fetchWeather() {
        memoryRepositoryApixu
                .getWeather("madrid")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError {
                    Log.e("------------------", "-----------> ERROR: ${it.message}")
                }
                .subscribe { weather ->
                    Log.e("------------------", "-----------> WEATHER: ${weather.location.name} updated at ${weather.lastUpdated}")
                }
    }
}
