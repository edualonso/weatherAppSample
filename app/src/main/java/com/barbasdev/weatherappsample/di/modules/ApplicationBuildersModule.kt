package com.barbasdev.weatherappsample.di.modules

import com.barbasdev.weatherappsample.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 *
 */
@Module
abstract class ApplicationBuildersModule {

    @ContributesAndroidInjector
    internal abstract fun contributesMainActivityInjector(): MainActivity

}