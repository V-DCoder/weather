package com.apps.demo.apixuweather.di.modules

import android.content.Context
import com.apps.demo.apixuweather.WeatherForecastApplication
import com.apps.demo.apixuweather.location.LocationManager
import dagger.Module
import dagger.Provides

@Module
class AppModule(val application: WeatherForecastApplication) {

    @Provides
    fun provideContext(): Context {
        return application
    }


    @Provides
    fun provideLocationManager(context: Context): LocationManager {
        return LocationManager(context)
    }


}