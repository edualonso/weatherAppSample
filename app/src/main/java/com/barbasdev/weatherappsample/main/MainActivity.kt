package com.barbasdev.weatherappsample.main

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.barbasdev.weatherappsample.R
import com.barbasdev.weatherappsample.base.BaseActivity
import com.barbasdev.weatherappsample.core.persistence.Repository
import com.barbasdev.weatherappsample.di.RepositoryConstants
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

    // Dagger: in-memory repositories
    @Inject @field:Named(RepositoryConstants.REPOSITORY_MEMORY_APIXU)
    lateinit var daggerMemoryRepositoryApixu: Repository

    @Inject @field:Named(RepositoryConstants.REPOSITORY_MEMORY_OPENWEATHER)
    lateinit var daggerMemoryRepositoryOpenWeather: Repository


    // Dagger: Room repositories
    @Inject @field:Named(RepositoryConstants.REPOSITORY_ROOM_APIXU)
    lateinit var daggerRoomRepositoryApixu: Repository

    @Inject @field:Named(RepositoryConstants.REPOSITORY_ROOM_OPENWEATHER)
    lateinit var daggerRoomRepositoryOpenWeather: Repository


    // Dagger: Realm repositories
    @Inject @field:Named(RepositoryConstants.REPOSITORY_REALM_APIXU)
    lateinit var daggerRealmRepositoryApixu: Repository

    @Inject @field:Named(RepositoryConstants.REPOSITORY_REALM_OPENWEATHER)
    lateinit var daggerRealmRepositoryOpenWeather: Repository


    // Koin: in-memory repositories
    private val koinMemoryRepositoryApixu: Repository by inject(RepositoryConstants.REPOSITORY_MEMORY_APIXU)
    private val koinMemoryRepositoryOpenWeather: Repository by inject(RepositoryConstants.REPOSITORY_MEMORY_OPENWEATHER)


    // Koin: Room repositories
    private val koinRoomRepositoryApixu: Repository by inject(RepositoryConstants.REPOSITORY_ROOM_APIXU)
    private val koinRoomRepositoryOpenWeather: Repository by inject(RepositoryConstants.REPOSITORY_ROOM_OPENWEATHER)


    // Koin: Realm repositories
    private val koinRealmRepositoryApixu: Repository by inject(RepositoryConstants.REPOSITORY_REALM_APIXU)
    private val koinRealmRepositoryOpenWeather: Repository by inject(RepositoryConstants.REPOSITORY_REALM_OPENWEATHER)


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
        // dagger
        disposables.add(RxView.clicks(findViewById<Button>(R.id.buttonDaggerMemoryApixu))
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribe {
                    Log.e("------------------", "-----------> FETCHING WEATHER - APIXU")
                    fetchWeather(koinMemoryRepositoryApixu, "APX")
                })

        disposables.add(RxView.clicks(findViewById<Button>(R.id.buttonDaggerMemoryOpenWeather))
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribe {
                    Log.e("------------------", "-----------> FETCHING WEATHER - OPENWEATHER")
                    fetchWeather(koinMemoryRepositoryOpenWeather, "OPN")
                })

        disposables.add(RxView.clicks(findViewById<Button>(R.id.buttonDaggerRoomApixu))
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribe {
                    Log.e("------------------", "-----------> FETCHING WEATHER - APIXU")
                    fetchWeather(koinRoomRepositoryApixu, "APX")
                })

        disposables.add(RxView.clicks(findViewById<Button>(R.id.buttonDaggerRoomOpenWeather))
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribe {
                    Log.e("------------------", "-----------> FETCHING WEATHER - OPENWEATHER")
                    fetchWeather(koinRoomRepositoryOpenWeather, "OPN")
                })

        disposables.add(RxView.clicks(findViewById<Button>(R.id.buttonDaggerRealmApixu))
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribe {
                    Log.e("------------------", "-----------> FETCHING WEATHER - APIXU")
                    fetchWeather(koinRealmRepositoryApixu, "APX")
                })

        disposables.add(RxView.clicks(findViewById<Button>(R.id.buttonDaggerRealmOpenWeather))
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribe {
                    Log.e("------------------", "-----------> FETCHING WEATHER - OPENWEATHER")
                    fetchWeather(koinRealmRepositoryOpenWeather, "OPN")
                })

        // koin
        disposables.add(RxView.clicks(findViewById<Button>(R.id.buttonKoinMemoryApixu))
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribe {
                    Log.e("------------------", "-----------> FETCHING WEATHER - APIXU")
                    fetchWeather(daggerMemoryRepositoryApixu, "APX")
                })

        disposables.add(RxView.clicks(findViewById<Button>(R.id.buttonKoinMemoryOpenWeather))
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribe {
                    Log.e("------------------", "-----------> FETCHING WEATHER - OPENWEATHER")
                    fetchWeather(daggerMemoryRepositoryOpenWeather, "OPN")
                })

        disposables.add(RxView.clicks(findViewById<Button>(R.id.buttonKoinRoomApixu))
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribe {
                    Log.e("------------------", "-----------> FETCHING WEATHER - APIXU")
                    fetchWeather(daggerRoomRepositoryApixu, "APX")
                })

        disposables.add(RxView.clicks(findViewById<Button>(R.id.buttonKoinRoomOpenWeather))
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribe {
                    Log.e("------------------", "-----------> FETCHING WEATHER - OPENWEATHER")
                    fetchWeather(daggerRoomRepositoryOpenWeather, "OPN")
                })

        disposables.add(RxView.clicks(findViewById<Button>(R.id.buttonKoinRealmApixu))
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribe {
                    Log.e("------------------", "-----------> FETCHING WEATHER - APIXU")
                    fetchWeather(daggerRealmRepositoryApixu, "APX")
                })

        disposables.add(RxView.clicks(findViewById<Button>(R.id.buttonKoinRealmOpenWeather))
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribe {
                    Log.e("------------------", "-----------> FETCHING WEATHER - OPENWEATHER")
                    fetchWeather(daggerRealmRepositoryOpenWeather, "OPN")
                })
    }

    private fun fetchWeather(repository: Repository, api: String) {
        disposables.add(repository
                .getWeatherWrapped("Madrid")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.e("------------------", "-----------> WEATHER: ${it.weather.location.name} updated at ${Date(it.weather.lastUpdated)}")
                    Toast.makeText(this, "$api ${it.source} - expires in: ${it.weather.lastUpdated + Repository.EXPIRATION_TIME - System.currentTimeMillis()} ms", Toast.LENGTH_SHORT).show()
                }, {
                    Log.e("------------------", "-----------> ERROR: ${it.message}")
                    Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show()
                }))
    }
}
