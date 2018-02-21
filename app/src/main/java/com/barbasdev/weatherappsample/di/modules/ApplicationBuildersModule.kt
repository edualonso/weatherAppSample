package com.barbasdev.weatherappsample.di.modules

import com.barbasdev.weatherappsample.MainActivity
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