package com.barbasdev.weatherappsample.core.persistence.memory

import com.barbasdev.weatherappsample.base.TestApplication
import com.barbasdev.weatherappsample.core.network.apixu.ApixuApiClientDelegateTest
import com.barbasdev.weatherappsample.core.persistence.Repository
import com.barbasdev.weatherappsample.di.module.TestNetworkConstModule
import com.barbasdev.weatherappsample.di.modules.RepositoryModule
import io.reactivex.Observable
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Ignore
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
    @Ignore
    fun myTest() {
        val singleThatDoesNotEmit = Observable.create<Int> {
            it.onComplete()
        }
        val singleThatEmits= Observable.create<Int> {
            it.onNext(42)
            it.onComplete()
        }

        val result1 = Observable
                .concat(
                        singleThatDoesNotEmit,
                        singleThatEmits
                )
                .test()
                .await()

        val akjsdhasdh = 0
    }

    @Test
    fun getWeatherApixu() {
        server.enqueue(MockResponse().setBody(ApixuApiClientDelegateTest.JSON.RESPONSE_WEATHER))

        val weather = apixuMemoryRepository
                .getWeather("London")
                .test()
                .await()
                .values()[0]

        val weather2 = apixuMemoryRepository
                .getWeather("London")
                .test()
                .await()
                .values()[0]

        val weather3 = apixuMemoryRepository
                .getWeather("London")
                .test()
                .await()
                .values()[0]

        val akjshd = 0
    }

}