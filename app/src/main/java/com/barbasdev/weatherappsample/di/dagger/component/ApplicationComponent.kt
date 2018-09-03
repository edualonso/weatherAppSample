package com.barbasdev.weatherappsample.di.dagger.component

import com.barbasdev.weatherappsample.base.WeatherApplication
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
        NetworkConstModule::class,
        RepositoryModule::class
))
abstract class ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(baseApplication: WeatherApplication): Builder
        fun build(): ApplicationComponent
    }

    abstract fun inject(weatherApplication: WeatherApplication)

}