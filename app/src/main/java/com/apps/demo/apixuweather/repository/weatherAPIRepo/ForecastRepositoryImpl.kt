package com.apps.demo.apixuweather.repository.weatherAPIRepo

import com.apps.demo.apixuweather.model.ForecastResponse
import com.apps.demo.apixuweather.utils.APIXUService
import io.reactivex.Single
import javax.inject.Inject


class ForecastRepositoryImpl
@Inject constructor(val service: APIXUService) : ForecastRepository {


    override fun getTodaysForecast(latLong: String): Single<ForecastResponse> {
        return service.getCurrentForecast(latLong)
    }
}