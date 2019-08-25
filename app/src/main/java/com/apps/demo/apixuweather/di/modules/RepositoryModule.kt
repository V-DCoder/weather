package com.apps.demo.apixuweather.di.modules

import com.apps.demo.apixuweather.repository.RepositoryImpl
import com.apps.demo.apixuweather.repository.weatherAPIRepo.ForecastRepository
import com.apps.demo.apixuweather.repository.weatherAPIRepo.ForecastRepositoryImpl
import com.apps.demo.apixuweather.utils.APIXUService
import com.apps.demo.apixuweather.utils.HttpInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class RepositoryModule {

    @Provides
    fun provideRepository(forecastRepository: ForecastRepository): RepositoryImpl {
        return RepositoryImpl(forecastRepository)
    }

    @Provides
    fun provideForcastRepository(apixuService: APIXUService): ForecastRepository {
        return ForecastRepositoryImpl(apixuService)
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
    internal fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(getBaseUrl())
            .client(okHttpClient)
            .build()
    }

    protected fun getBaseUrl(): String {
        return "https://api.apixu.com/v1/"
    }

    @Provides
    @Singleton
    fun provideAPIXUService(retrofit: Retrofit): APIXUService {
        return retrofit.create(APIXUService::class.java)
    }


}