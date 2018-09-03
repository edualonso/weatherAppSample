package com.barbasdev.weatherappsample.di.dagger.modules

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