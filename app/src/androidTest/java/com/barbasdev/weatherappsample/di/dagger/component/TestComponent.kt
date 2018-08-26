package com.barbasdev.weatherappsample.di.dagger.component

import com.barbasdev.weatherappsample.base.TestApplication
import com.barbasdev.weatherappsample.base.WeatherApplication
import com.barbasdev.weatherappsample.core.network.apixu.ApixuApiClientTest
import com.barbasdev.weatherappsample.core.network.openweather.OpenWeatherApiClientTest
import com.barbasdev.weatherappsample.core.persistence.memory.MemoryRepositoryTest
import com.barbasdev.weatherappsample.core.persistence.realm.RealmRepositoryTest
import com.barbasdev.weatherappsample.core.persistence.room.RoomRepositoryTest
import com.barbasdev.weatherappsample.di.dagger.modules.*
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
    abstract fun inject(apixuApiClientDelegateTest: ApixuApiClientTest)
    abstract fun inject(openWeatherApiClientDelegateTest: OpenWeatherApiClientTest)
    abstract fun inject(memoryRepositoryDelegateTest: MemoryRepositoryTest)
    abstract fun inject(roomRepositoryDelegateTest: RoomRepositoryTest)
    abstract fun inject(realmRepositoryDelegateTest: RealmRepositoryTest)

}