package com.barbasdev.weatherappsample.core.persistence.realm

import com.barbasdev.weatherappsample.base.TestApplication
import com.barbasdev.weatherappsample.core.network.apixu.ApixuApiClientDelegateTest
import com.barbasdev.weatherappsample.core.network.openweather.OpenWeatherApiClientDelegateTest
import com.barbasdev.weatherappsample.core.persistence.Repository
import com.barbasdev.weatherappsample.core.persistence.WeatherResultsTestHelper
import com.barbasdev.weatherappsample.di.dagger.modules.RepositoryModule
import com.barbasdev.weatherappsample.di.dagger.modules.TestNetworkConstModule
import junit.framework.Assert
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.standalone.inject
import org.koin.test.KoinTest
import javax.inject.Inject
import javax.inject.Named

class RealmRepositoryDelegateTest : KoinTest {

    // dagger
    @Inject
    @field:Named(RepositoryModule.REPOSITORY_MEMORY_APIXU)
    lateinit var daggerApixuRoomRepository: Repository

    @Inject
    @field:Named(RepositoryModule.REPOSITORY_MEMORY_OPENWEATHER)
    lateinit var daggerOpenWeatherMemoryRepository: Repository


    // koin
    private val koinApixuRoomRepository: Repository by inject(RepositoryModule.REPOSITORY_MEMORY_APIXU)
    private val koinOpenWeatherRoomRepository: Repository by inject(RepositoryModule.REPOSITORY_MEMORY_OPENWEATHER)


    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        TestApplication.testComponent
                .inject(this)

        server = MockWebServer().apply {
            start(TestNetworkConstModule.SERVER_PORT)
        }
    }

    @After
    fun tearDown() {
        server.close()
    }

    @Test
    fun getWeatherApixu() {
        server.enqueue(MockResponse().setBody(ApixuApiClientDelegateTest.JSON.RESPONSE_WEATHER))
        WeatherResultsTestHelper.assertWeatherResultsApixu(daggerApixuRoomRepository)
        server.enqueue(MockResponse().setBody(ApixuApiClientDelegateTest.JSON.RESPONSE_WEATHER))
        WeatherResultsTestHelper.assertWeatherResultsApixu(koinApixuRoomRepository)
    }

    @Test
    fun getWeatherOpenWeather() {
        server.enqueue(MockResponse().setBody(OpenWeatherApiClientDelegateTest.JSON.RESPONSE_WEATHER))
        WeatherResultsTestHelper.assertWeatherResultsOpenWeather(daggerOpenWeatherMemoryRepository)
        server.enqueue(MockResponse().setBody(OpenWeatherApiClientDelegateTest.JSON.RESPONSE_WEATHER))
        WeatherResultsTestHelper.assertWeatherResultsOpenWeather(koinOpenWeatherRoomRepository)
    }
}