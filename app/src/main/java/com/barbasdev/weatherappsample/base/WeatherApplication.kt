package com.barbasdev.weatherappsample.base

import android.app.Activity
import android.support.multidex.MultiDexApplication
import com.barbasdev.weatherappsample.di.dagger.component.ApplicationComponent
import com.barbasdev.weatherappsample.di.dagger.component.DaggerApplicationComponent
import com.barbasdev.weatherappsample.di.koin.Modules
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.realm.Realm
import org.koin.android.ext.android.startKoin
import javax.inject.Inject

/**
 * Created by edu on 21/02/2018.
 */
open class WeatherApplication : MultiDexApplication(), HasActivityInjector {

    @Inject lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        // initialise Realm before Dagger (or else generating a RealmConfiguration will make Realm crash
        Realm.init(this)

        init()
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingActivityInjector
    }

    /**
     * Override in custom application test class.
     */
    open fun init() {
        // dagger
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationContext(this)
                .build()
        applicationComponent.inject(this)

        // koin
        val modules = listOf(
                Modules.networkConstModule,
                Modules.networkModule,
                Modules.databaseModule,
                Modules.repositoryModule
        )
        startKoin(this, modules)
    }


    companion object {
        internal lateinit var applicationComponent: ApplicationComponent
    }
}
