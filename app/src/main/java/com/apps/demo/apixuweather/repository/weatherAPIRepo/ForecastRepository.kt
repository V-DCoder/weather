package com.apps.demo.apixuweather.repository.weatherAPIRepo

import com.apps.demo.apixuweather.model.ForecastResponse
import io.reactivex.Single

interface ForecastRepository {

    fun getTodaysForecast(latLong : String): Single<ForecastResponse>
}