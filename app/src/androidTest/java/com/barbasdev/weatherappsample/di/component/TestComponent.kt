package com.barbasdev.weatherappsample.di.component

import com.barbasdev.weatherappsample.base.TestApplication
import com.barbasdev.weatherappsample.base.WeatherApplication
import com.barbasdev.weatherappsample.core.network.apixu.ApixuApiClientDelegateTest
import com.barbasdev.weatherappsample.core.network.openweather.OpenWeatherApiClientDelegateTest
import com.barbasdev.weatherappsample.core.persistence.memory.MemoryRepositoryDelegateTest
import com.barbasdev.weatherappsample.di.module.TestNetworkConstModule
import com.barbasdev.weatherappsample.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 *
 */
@Singleton
@Component(modules = arrayOf(
        AndroidInjectionModule::class,
        ApplicationBuildersModule::class,
        ApplicationModule::class,
        ActivityModule::class,
        DatabaseModule::class,
        NetworkModule::class,
        TestNetworkConstModule::class,
        RepositoryModule::class
))
abstract class TestComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(baseApplication: WeatherApplication): Builder
        fun build(): TestComponent
    }

    abstract fun inject(testApplication: TestApplication)
    abstract fun inject(apixuApiClientDelegateTest: ApixuApiClientDelegateTest)
    abstract fun inject(openWeatherApiClientDelegateTest: OpenWeatherApiClientDelegateTest)
    abstract fun inject(memoryRepositoryDelegateTest: MemoryRepositoryDelegateTest)

}