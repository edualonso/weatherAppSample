package com.barbasdev.weatherappsample.core.persistence.memory

import com.barbasdev.weatherappsample.base.TestApplication
import com.barbasdev.weatherappsample.core.network.apixu.ApixuApiClientDelegateTest
import com.barbasdev.weatherappsample.core.persistence.Repository
import com.barbasdev.weatherappsample.di.module.TestNetworkConstModule
import com.barbasdev.weatherappsample.di.dagger.modules.RepositoryModule
import junit.framework.Assert.assertEquals
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by edu on 24/02/2018.
 */
class MemoryRepositoryDelegateTest {

    @Inject
    @field:Named(RepositoryModule.REPOSITORY_MEMORY_APIXU)
    lateinit var apixuMemoryRepository: Repository

    @Inject
    @field:Named(RepositoryModule.REPOSITORY_MEMORY_OPENWEATHER)
    lateinit var openWeatherMemoryRepository: Repository

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

        val weather = apixuMemoryRepository
                .getWeather("London")
                .test()
                .await()
                .values()[0]

        assertEquals("London", weather.location.name)
        assertEquals("United Kingdom", weather.location.country)
        assertEquals(51.52F, weather.location.lat)
        assertEquals(-0.11F, weather.location.lon)
        assertEquals(2F, weather.temperature)
    }

}