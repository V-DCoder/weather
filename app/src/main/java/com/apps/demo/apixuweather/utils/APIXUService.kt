package com.apps.demo.apixuweather.utils

import com.gojeck.apps.whertherly.model.ForecastResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface APIXUService {

    @GET("forecast.json")
    fun getCurrentForecast(@Query("key") key: String, @Query("q") city: String, @Query("days") days: Int): Single<ForecastResponse>

}