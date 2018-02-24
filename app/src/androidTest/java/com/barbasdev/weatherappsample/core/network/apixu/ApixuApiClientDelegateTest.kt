package com.barbasdev.weatherappsample.core.network.apixu

import com.barbasdev.weatherappsample.base.TestApplication
import com.barbasdev.weatherappsample.core.network.ApiClient
import com.barbasdev.weatherappsample.di.module.TestNetworkConstModule
import com.barbasdev.weatherappsample.di.modules.NetworkModule
import junit.framework.Assert
import junit.framework.Assert.assertEquals
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by edu on 22/02/2018.
 */
class ApixuApiClientDelegateTest {

    @Inject
    @field:Named(NetworkModule.APIXU_API_CLIENT)
    lateinit var apixuApiClient: ApiClient

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
    fun getLocation() {
        server.enqueue(MockResponse().setBody(JSON.RESPONSE_LOCATION))

        val locations = apixuApiClient
                .getLocation("KAHSDKHEJKHKHASKDHJ")
                .test()
                .await()
                .values()[0]

        Assert.assertEquals("Entrevias, Madrid, Spain", locations[0].name)
        Assert.assertEquals("Madrid, Madrid, Spain", locations[1].name)
        Assert.assertEquals("Puente De Vallecas, Madrid, Spain", locations[2].name)
        Assert.assertEquals("Barrio Puente De Vallecas, Madrid, Spain", locations[3].name)
        Assert.assertEquals("Barrio Moratalaz, Madrid, Spain", locations[4].name)
        Assert.assertEquals("Moratalaz, Madrid, Spain", locations[5].name)
        Assert.assertEquals("Pueblo Nuevo-Ventas, Madrid, Spain", locations[6].name)
        Assert.assertEquals("Barrio Pueblo Nuevo-Ventas, Madrid, Spain", locations[7].name)
        Assert.assertEquals("Carabanchel Bajo, Madrid, Spain", locations[8].name)
        Assert.assertEquals("Barrio Pavones, Madrid, Spain", locations[9].name)
    }

    @Test
    fun getWeather() {
        server.enqueue(MockResponse().setBody(JSON.RESPONSE_WEATHER))

        val weather = apixuApiClient
                .getWeather("AHSDASJDahjsdgas21")
                .test()
                .await()
                .values()[0]

        assertEquals(weather.lastUpdated, 1519338644L)
        assertEquals(weather.location.id, 0)
        assertEquals(weather.location.name, "London")
        assertEquals(weather.location.country, "United Kingdom")
        assertEquals(weather.location.lat, 51.52F)
        assertEquals(weather.location.lon, -0.11F)
    }

    object JSON {
        const val RESPONSE_LOCATION =
                """
[
  {
    "id": 708204,
    "name": "Entrevias, Madrid, Spain",
    "region": "Madrid",
    "country": "Spain",
    "lat": 40.4,
    "lon": -3.68,
    "url": "entrevias-madrid-spain"
  },
  {
    "id": 714482,
    "name": "Madrid, Madrid, Spain",
    "region": "Madrid",
    "country": "Spain",
    "lat": 40.4,
    "lon": -3.68,
    "url": "madrid-madrid-spain"
  },
  {
    "id": 719178,
    "name": "Puente De Vallecas, Madrid, Spain",
    "region": "Madrid",
    "country": "Spain",
    "lat": 40.4,
    "lon": -3.65,
    "url": "puente-de-vallecas-madrid-spain"
  },
  {
    "id": 699183,
    "name": "Barrio Puente De Vallecas, Madrid, Spain",
    "region": "Madrid",
    "country": "Spain",
    "lat": 40.4,
    "lon": -3.65,
    "url": "barrio-puente-de-vallecas-madrid-spain"
  },
  {
    "id": 699155,
    "name": "Barrio Moratalaz, Madrid, Spain",
    "region": "Madrid",
    "country": "Spain",
    "lat": 40.42,
    "lon": -3.65,
    "url": "barrio-moratalaz-madrid-spain"
  },
  {
    "id": 716110,
    "name": "Moratalaz, Madrid, Spain",
    "region": "Madrid",
    "country": "Spain",
    "lat": 40.42,
    "lon": -3.65,
    "url": "moratalaz-madrid-spain"
  },
  {
    "id": 719142,
    "name": "Pueblo Nuevo-Ventas, Madrid, Spain",
    "region": "Madrid",
    "country": "Spain",
    "lat": 40.42,
    "lon": -3.63,
    "url": "pueblo-nuevo-ventas-madrid-spain"
  },
  {
    "id": 699181,
    "name": "Barrio Pueblo Nuevo-Ventas, Madrid, Spain",
    "region": "Madrid",
    "country": "Spain",
    "lat": 40.42,
    "lon": -3.63,
    "url": "barrio-pueblo-nuevo-ventas-madrid-spain"
  },
  {
    "id": 701409,
    "name": "Carabanchel Bajo, Madrid, Spain",
    "region": "Madrid",
    "country": "Spain",
    "lat": 40.38,
    "lon": -3.73,
    "url": "carabanchel-bajo-madrid-spain"
  },
  {
    "id": 699173,
    "name": "Barrio Pavones, Madrid, Spain",
    "region": "Madrid",
    "country": "Spain",
    "lat": 40.4,
    "lon": -3.62,
    "url": "barrio-pavones-madrid-spain"
  }
]
"""

        const val RESPONSE_WEATHER =
"""
{
    "location": {
        "name": "London",
        "region": "City of London, Greater London",
        "country": "United Kingdom",
        "lat": 51.52,
        "lon": -0.11,
        "tz_id": "Europe/London",
        "localtime_epoch": 1519338701,
        "localtime": "2018-02-22 22:31"
    },
    "current": {
        "last_updated_epoch": 1519338644,
        "last_updated": "2018-02-22 22:30",
        "temp_c": 2,
        "temp_f": 35.6,
        "is_day": 0,
        "condition": {
            "text": "Clear",
            "icon": "//cdn.apixu.com/weather/64x64/night/113.png",
            "code": 1000
        },
        "wind_mph": 4.3,
        "wind_kph": 6.8,
        "wind_degree": 60,
        "wind_dir": "ENE",
        "pressure_mb": 1023,
        "pressure_in": 30.7,
        "precip_mm": 0,
        "precip_in": 0,
        "humidity": 75,
        "cloud": 0,
        "feelslike_c": 0,
        "feelslike_f": 32,
        "vis_km": 10,
        "vis_miles": 6
    }
}
"""
    }
}