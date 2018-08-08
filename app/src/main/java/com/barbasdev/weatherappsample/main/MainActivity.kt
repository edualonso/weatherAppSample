package com.barbasdev.weatherappsample.main

import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.barbasdev.weatherappsample.R
import com.barbasdev.weatherappsample.base.BaseActivity
import com.barbasdev.weatherappsample.core.persistence.Repository
import com.barbasdev.weatherappsample.di.dagger.modules.RepositoryModule
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Named

class MainActivity : BaseActivity() {

//    // In-memory repositories
//    @Inject @field:Named(RepositoryModule.REPOSITORY_MEMORY_APIXU)
//    lateinit var memoryRepositoryApixu: Repository
//
//    @Inject @field:Named(RepositoryModule.REPOSITORY_MEMORY_OPENWEATHER)
//    lateinit var memoryRepositoryOpenWeather: Repository
//
//
//    // Room repositories
//    @Inject @field:Named(RepositoryModule.REPOSITORY_ROOM_APIXU)
//    lateinit var roomRepositoryApixu: Repository
//
//    @Inject @field:Named(RepositoryModule.REPOSITORY_ROOM_OPENWEATHER)
//    lateinit var roomRepositoryOpenWeather: Repository
//
//
//    // Realm repositories
//    @Inject @field:Named(RepositoryModule.REPOSITORY_REALM_APIXU)
//    lateinit var realmRepositoryApixu: Repository
//
//    @Inject @field:Named(RepositoryModule.REPOSITORY_REALM_OPENWEATHER)
//    lateinit var realmRepositoryOpenWeather: Repository


    // In-memory repositories
    private val memoryRepositoryApixu: Repository by inject(RepositoryModule.REPOSITORY_MEMORY_APIXU)
    private val memoryRepositoryOpenWeather: Repository by inject(RepositoryModule.REPOSITORY_MEMORY_OPENWEATHER)


    // Room repositories
    private val roomRepositoryApixu: Repository by inject(RepositoryModule.REPOSITORY_ROOM_APIXU)
    private val roomRepositoryOpenWeather: Repository by inject(RepositoryModule.REPOSITORY_ROOM_OPENWEATHER)


    // Realm repositories
    private val realmRepositoryApixu: Repository by inject(RepositoryModule.REPOSITORY_REALM_APIXU)
    private val realmRepositoryOpenWeather: Repository by inject(RepositoryModule.REPOSITORY_REALM_OPENWEATHER)

    private val disposables = CompositeDisposable()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindListeners()
    }

    override fun onPause() {
        super.onPause()

        disposables.clear()
    }

    private fun bindListeners() {
        disposables.add(RxView.clicks(findViewById<Button>(R.id.buttonMemoryApixu))
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribe {
                    Log.e("------------------", "-----------> FETCHING WEATHER - APIXU")
                    fetchWeather(memoryRepositoryApixu)
                })

        disposables.add(RxView.clicks(findViewById<Button>(R.id.buttonMemoryOpenWeather))
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribe {
                    Log.e("------------------", "-----------> FETCHING WEATHER - OPENWEATHER")
                    fetchWeather(memoryRepositoryOpenWeather)
                })

        disposables.add(RxView.clicks(findViewById<Button>(R.id.buttonRoomApixu))
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribe {
                    Log.e("------------------", "-----------> FETCHING WEATHER - APIXU")
                    fetchWeather(roomRepositoryApixu)
                })

        disposables.add(RxView.clicks(findViewById<Button>(R.id.buttonRoomOpenWeather))
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribe {
                    Log.e("------------------", "-----------> FETCHING WEATHER - OPENWEATHER")
                    fetchWeather(roomRepositoryOpenWeather)
                })

        disposables.add(RxView.clicks(findViewById<Button>(R.id.buttonRealmApixu))
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribe {
                    Log.e("------------------", "-----------> FETCHING WEATHER - APIXU")
                    fetchWeather(realmRepositoryApixu)
                })

        disposables.add(RxView.clicks(findViewById<Button>(R.id.buttonRealmOpenWeather))
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribe {
                    Log.e("------------------", "-----------> FETCHING WEATHER - OPENWEATHER")
                    fetchWeather(realmRepositoryOpenWeather)
                })
    }

    private fun fetchWeather(repository: Repository) {
        disposables.add(repository
                .getWeather("Madrid")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ weather ->
                    Log.e("------------------", "-----------> WEATHER: ${weather.location.name} updated at ${Date(weather.lastUpdated)}")
                }, {
                    Log.e("------------------", "-----------> ERROR: ${it.message}")
                }))
    }
}
