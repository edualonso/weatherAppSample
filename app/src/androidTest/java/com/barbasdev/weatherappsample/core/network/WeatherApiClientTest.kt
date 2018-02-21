package com.barbasdev.weatherappsample.core.network

import com.barbasdev.weatherappsample.base.TestApplication
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

/**
 * Created by edu on 21/02/2018.
 */
class WeatherApiClientTest {

    @Inject
    lateinit var weatherApiClient: WeatherApiClient

    @Before
    fun setUp() {
        TestApplication.testComponent
                .inject(this)
    }

    @Test
    fun getLocation() {
        val locations = weatherApiClient
                .getLocation("madrid")
                .test()
                .await()
                .values()[0]

        assertEquals("Entrevias, Madrid, Spain", locations[0].name)
        assertEquals("Madrid, Madrid, Spain", locations[1].name)
        assertEquals("Puente De Vallecas, Madrid, Spain", locations[2].name)
        assertEquals("Barrio Puente De Vallecas, Madrid, Spain", locations[3].name)
        assertEquals("Barrio Moratalaz, Madrid, Spain", locations[4].name)
        assertEquals("Moratalaz, Madrid, Spain", locations[5].name)
        assertEquals("Pueblo Nuevo-Ventas, Madrid, Spain", locations[6].name)
        assertEquals("Barrio Pueblo Nuevo-Ventas, Madrid, Spain", locations[7].name)
        assertEquals("Carabanchel Bajo, Madrid, Spain", locations[8].name)
        assertEquals("Barrio Pavones, Madrid, Spain", locations[9].name)
    }

}