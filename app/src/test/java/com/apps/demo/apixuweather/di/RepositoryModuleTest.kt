package com.apps.demo.apixuweather.di

import com.apps.demo.apixuweather.di.modules.OBSERVER_ON
import com.apps.demo.apixuweather.di.modules.SUBCRIBER_ON
import com.apps.demo.apixuweather.repository.RepositoryImpl
import com.apps.demo.apixuweather.repository.weatherAPIRepo.ForecastRepository
import com.apps.demo.apixuweather.repository.weatherAPIRepo.ForecastRepositoryImpl
import com.apps.demo.apixuweather.utils.APIXUService
import com.apps.demo.apixuweather.utils.HttpInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
class RepositoryModuleTest(private val baseUrl: String) {

    @Provides
    fun provideRepository(forecastRepository: ForecastRepository): RepositoryImpl {
        return RepositoryImpl(forecastRepository)
    }

    @Provides
    fun provideForcastRepository(
        apixuService: APIXUService, @Named(SUBCRIBER_ON) subscriberOn: Scheduler,
        @Named(OBSERVER_ON) observerOn: Scheduler
    ): ForecastRepository {
        return ForecastRepositoryImpl(apixuService,subscriberOn,observerOn)
    }


    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return loggingInterceptor
    }


    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }


    @Provides
    @Singleton
    fun provideCacheOverrideInterceptor(): Interceptor {
        return HttpInterceptor()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        cacheOverrideInterceptor: Interceptor
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addNetworkInterceptor(cacheOverrideInterceptor)
        return builder
            .build()

    }


    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()
    }


    @Provides
    @Singleton
    fun provideAPIXUService(retrofit: Retrofit): APIXUService {
        return retrofit.create(APIXUService::class.java)
    }


}