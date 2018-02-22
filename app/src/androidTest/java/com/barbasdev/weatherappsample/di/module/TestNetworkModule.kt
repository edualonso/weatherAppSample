package com.barbasdev.weatherappsample.di.module

import dagger.Module
import dagger.Provides
import okhttp3.mockwebserver.MockWebServer
import javax.inject.Singleton

/**
 *
 */
@Module
class TestNetworkModule {

    @Provides
    @Singleton
    fun providesMockWebServer(): MockWebServer
            = MockWebServer()

}