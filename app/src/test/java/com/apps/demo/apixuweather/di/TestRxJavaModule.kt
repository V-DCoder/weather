package io.github.philippeboisney.retrokotlin.di

import com.apps.demo.apixuweather.di.modules.OBSERVER_ON
import com.apps.demo.apixuweather.di.modules.SUBCRIBER_ON
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
class TestRxJavaModule {

    @Provides
    @Named(SUBCRIBER_ON)
    @Singleton
    fun provideSuscriberOn(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    @Named(OBSERVER_ON)
    @Singleton
    fun provideObserverOn(): Scheduler = AndroidSchedulers.mainThread()
}