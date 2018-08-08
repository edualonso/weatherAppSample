package com.barbasdev.weatherappsample.base

import com.barbasdev.weatherappsample.di.dagger.component.DaggerTestComponent
import com.barbasdev.weatherappsample.di.dagger.component.TestComponent

/**
 *
 */
class TestApplication : WeatherApplication() {

    override fun init() {
        testComponent = DaggerTestComponent
                .builder()
                .applicationContext(this)
                .build()
        testComponent.inject(this)
    }

    companion object {
        internal lateinit var testComponent: TestComponent
    }
}