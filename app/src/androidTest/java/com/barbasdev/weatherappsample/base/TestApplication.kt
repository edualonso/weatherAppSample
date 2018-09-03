package com.barbasdev.weatherappsample.base

import com.barbasdev.weatherappsample.di.dagger.component.DaggerTestComponent
import com.barbasdev.weatherappsample.di.dagger.component.TestComponent
import com.barbasdev.weatherappsample.di.koin.Modules
import com.barbasdev.weatherappsample.di.koin.TestModules
import org.koin.android.ext.android.startKoin

/**
 *
 */
class TestApplication : WeatherApplication() {

    override fun init() {
        // dagger
        testComponent = DaggerTestComponent
                .builder()
                .applicationContext(this)
                .build()
        testComponent.inject(this)

        // koin
        val modules = listOf(
                TestModules.networkTestConstModule,
                Modules.networkModule,
                TestModules.databaseTestModule,
                Modules.repositoryModule
        )
        startKoin(this, modules)
    }


    companion object {
        internal lateinit var testComponent: TestComponent
    }
}