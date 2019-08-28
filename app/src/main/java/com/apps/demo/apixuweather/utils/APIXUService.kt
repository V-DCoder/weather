package com.apps.demo.apixuweather.utils

import com.apps.demo.apixuweather.model.ForecastResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface APIXUService {

    @GET("forecast.json")
    fun getCurrentForecast( @Query("q") city: String): Single<ForecastResponse>

}