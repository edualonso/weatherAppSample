package com.barbasdev.weatherappsample.core.network.openweather

import com.barbasdev.weatherappsample.base.TestApplication
import com.barbasdev.weatherappsample.core.network.WeatherApiClient
import com.barbasdev.weatherappsample.di.module.TestNetworkConstModule
import junit.framework.Assert
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

/**
 * Created by edu on 22/02/2018.
 */
class OpenWeatherWeatherApiClientImplTest {

    @Inject
    lateinit var server: MockWebServer
    @Inject
    lateinit var openWeatherDelegate: OpenWeatherWeatherApiClientImpl

    private val apixuWeatherApiClient by lazy { WeatherApiClient(openWeatherDelegate) }

    @Before
    fun setUp() {
        TestApplication.testComponent
                .inject(this)
    }

    @After
    fun tearDown() {
        server.close()
    }

    @Test
    fun getLocation() {
        server.start(TestNetworkConstModule.SERVER_PORT)
        server.enqueue(MockResponse().setBody(JSON.response))

        val locations = apixuWeatherApiClient
                .getLocation("madrid")
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

    object JSON {
        const val response =
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
    }
}