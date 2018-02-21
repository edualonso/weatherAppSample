package com.barbasdev.weatherappsample.base

import android.app.Activity
import android.support.multidex.MultiDexApplication
import com.barbasdev.weatherappsample.di.component.ApplicationComponent
import com.barbasdev.weatherappsample.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Inject

/**
 * Created by edu on 21/02/2018.
 */
open class WeatherApplication : MultiDexApplication(), HasActivityInjector {

    @Inject lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>
    @Inject lateinit var realmConfiguration: RealmConfiguration

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
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationContext(this)
                .build()
        applicationComponent.inject(this)
    }

    companion object {
        internal lateinit var applicationComponent: ApplicationComponent
    }
}