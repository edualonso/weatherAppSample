package com.barbasdev.weatherappsample.core.network.openweather

import com.barbasdev.weatherappsample.base.TestApplication
import com.barbasdev.weatherappsample.core.network.ApiClient
import com.barbasdev.weatherappsample.di.NetworkConstants
import com.barbasdev.weatherappsample.di.dagger.modules.NetworkModule
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

/**
 * Created by edu on 22/02/2018.
 */
class OpenWeatherApiClientTest : KoinTest {

    // dagger
    @Inject
    @field:Named(NetworkConstants.OPENWEATHER_API_CLIENT)
    lateinit var daggerOpenWeatherApiClient: ApiClient


    // koin
    private val koinOpenWeatherApiClient: ApiClient by inject(NetworkConstants.OPENWEATHER_API_CLIENT)


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
    fun getWeatherDagger() {
        server.enqueue(MockResponse().setBody(JSON.RESPONSE_WEATHER))
        assertWeatherResults(daggerOpenWeatherApiClient)
    }

    @Test
    fun getWeatherKoin() {
        server.enqueue(MockResponse().setBody(JSON.RESPONSE_WEATHER))
        assertWeatherResults(koinOpenWeatherApiClient)
    }

    private fun assertWeatherResults(apiClient: ApiClient) {
        val weather = apiClient
                .getWeather("AHSDASJDahjsdgas21")
                .test()
                .await()
                .values()[0]

        Assert.assertEquals(weather.location.id, 2643743L)
        Assert.assertEquals(weather.location.name, "London")
        Assert.assertEquals(weather.location.country, "GB")
        Assert.assertEquals(weather.location.lat, 51.51F)
        Assert.assertEquals(weather.location.lon, -0.13F)
    }


    object JSON {
        const val RESPONSE_LOCATION =
                """
[
  {
    "id": 707860,
    "name": "Hurzuf",
    "country": "UA",
    "coord": {
      "lon": 34.283333,
      "lat": 44.549999
    }
  },
  {
    "id": 519188,
    "name": "Novinki",
    "country": "RU",
    "coord": {
      "lon": 37.666668,
      "lat": 55.683334
    }
  }
]
"""

        const val RESPONSE_WEATHER =
"""
{
    "coord": {
        "lon": -0.13,
        "lat": 51.51
    },
    "weather": [
        {
            "id": 300,
            "main": "Drizzle",
            "description": "light intensity drizzle",
            "icon": "09d"
        }
    ],
    "base": "stations",
    "main": {
        "temp": 280.32,
        "pressure": 1012,
        "humidity": 81,
        "temp_min": 279.15,
        "temp_max": 281.15
    },
    "visibility": 10000,
    "wind": {
        "speed": 4.1,
        "deg": 80
    },
    "clouds": {
        "all": 90
    },
    "dt": 1485789600,
    "sys": {
        "type": 1,
        "id": 5091,
        "message": 0.0103,
        "country": "GB",
        "sunrise": 1485762037,
        "sunset": 1485794875
    },
    "id": 2643743,
    "name": "London",
    "cod": 200
}
"""
    }
}