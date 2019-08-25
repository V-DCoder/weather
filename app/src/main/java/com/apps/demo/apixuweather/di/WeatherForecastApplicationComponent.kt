package com.apps.demo.apixuweather.di

import com.apps.demo.apixuweather.WeatherForecastApplication
import com.apps.demo.apixuweather.di.modules.ActivityProvideModule
import com.apps.demo.apixuweather.di.modules.AppModule
import com.apps.demo.apixuweather.di.modules.RepositoryModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(
        AppModule::class,
        RepositoryModule::class,
        AndroidSupportInjectionModule::class,
        ActivityProvideModule::class
    )
)
interface WeatherForecastApplicationComponent {

    fun inject(application: WeatherForecastApplication)


}