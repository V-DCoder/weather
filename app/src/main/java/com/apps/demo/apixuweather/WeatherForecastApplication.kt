package com.apps.demo.apixuweather


import android.app.Activity
import android.app.Application
import com.apps.demo.apixuweather.di.DaggerWeatherForecastApplicationComponent
import com.apps.demo.apixuweather.di.modules.AppModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class WeatherForecastApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()


        val component = DaggerWeatherForecastApplicationComponent.builder()
            .appModule(AppModule(this))
            .build()
        component.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {

        return dispatchingAndroidInjector
    }

}