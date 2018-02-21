package com.barbasdev.weatherappsample.di.modules

import android.content.Context
import android.content.res.AssetManager
import com.barbasdev.weatherappsample.base.WeatherApplication
import dagger.Module
import dagger.Provides

/**
 *
 */
@Module
class ApplicationModule {

    @Provides
    fun providesContext(weatherApplication: WeatherApplication): Context {
        return weatherApplication.applicationContext
    }

    @Provides
    fun providesAssets(context: Context): AssetManager {
        return context.assets
    }

}