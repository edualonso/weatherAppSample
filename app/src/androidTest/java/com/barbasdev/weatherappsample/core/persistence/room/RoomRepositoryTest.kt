package com.barbasdev.weatherappsample.core.persistence.room

import com.barbasdev.weatherappsample.base.TestApplication
import com.barbasdev.weatherappsample.core.network.apixu.ApixuApiClientTest
import com.barbasdev.weatherappsample.core.network.openweather.OpenWeatherApiClientTest
import com.barbasdev.weatherappsample.core.persistence.Repository
import com.barbasdev.weatherappsample.core.persistence.WeatherResultsTestHelper
import com.barbasdev.weatherappsample.di.RepositoryConstants
import com.barbasdev.weatherappsample.di.dagger.modules.TestNetworkConstModule
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.standalone.inject
import org.koin.test.KoinTest
import javax.inject.Inject
import javax.inject.Named

class RoomRepositoryTest : KoinTest {

    // dagger
    @Inject
    @field:Named(RepositoryConstants.REPOSITORY_ROOM_APIXU)
    lateinit var daggerApixuRoomRepository: Repository

    @Inject
    @field:Named(RepositoryConstants.REPOSITORY_ROOM_OPENWEATHER)
    lateinit var daggerOpenWeatherRoomRepository: Repository


    // koin
    private val koinApixuRoomRepository: Repository by inject(RepositoryConstants.REPOSITORY_ROOM_APIXU)
    private val koinOpenWeatherRoomRepository: Repository by inject(RepositoryConstants.REPOSITORY_ROOM_OPENWEATHER)


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
    fun getWeatherApixuDagger() {
        server.enqueue(MockResponse().setBody(ApixuApiClientTest.JSON.RESPONSE_WEATHER))
        WeatherResultsTestHelper.assertWeatherResultsApixu(daggerApixuRoomRepository)
    }

    @Test
    fun getWeatherApixuKoin() {
       server.enqueue(MockResponse().setBody(ApixuApiClientTest.JSON.RESPONSE_WEATHER))
        WeatherResultsTestHelper.assertWeatherResultsApixu(koinApixuRoomRepository)
    }

    @Test
    fun getWeatherOpenWeatherDagger() {
        server.enqueue(MockResponse().setBody(OpenWeatherApiClientTest.JSON.RESPONSE_WEATHER))
        WeatherResultsTestHelper.assertWeatherResultsOpenWeather(daggerOpenWeatherRoomRepository)
    }

    @Test
    fun getWeatherOpenWeatherKoin() {
        server.enqueue(MockResponse().setBody(OpenWeatherApiClientTest.JSON.RESPONSE_WEATHER))
        WeatherResultsTestHelper.assertWeatherResultsOpenWeather(koinOpenWeatherRoomRepository)
    }
}