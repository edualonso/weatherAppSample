package com.barbasdev.weatherappsample.di.component

import com.barbasdev.weatherappsample.base.TestApplication
import com.barbasdev.weatherappsample.base.WeatherApplication
import com.barbasdev.weatherappsample.core.network.apixu.ApixuWeatherApiClientImplTest
import com.barbasdev.weatherappsample.core.network.openweather.OpenWeatherWeatherApiClientImplTest
import com.barbasdev.weatherappsample.di.module.TestNetworkConstModule
import com.barbasdev.weatherappsample.di.module.TestNetworkModule
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
        TestNetworkModule::class,
        TestNetworkConstModule::class
))
abstract class TestComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(baseApplication: WeatherApplication): Builder
        fun build(): TestComponent
    }

    abstract fun inject(testApplication: TestApplication)
    abstract fun inject(apixuWeatherApiClientImplTest: ApixuWeatherApiClientImplTest)
    abstract fun inject(openWeatherWeatherApiClientImplTest: OpenWeatherWeatherApiClientImplTest)

}