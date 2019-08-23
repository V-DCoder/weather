package com.apps.demo.apixuweather.repository.weatherAPIRepo

import com.apps.demo.apixuweather.utils.APIXUService
import com.gojeck.apps.whertherly.model.ForecastResponse
import io.reactivex.Single
import javax.inject.Inject


class ForecastRepositoryImpl
@Inject constructor(val service: APIXUService) : ForecastRepository {


    override fun getTodaysForecast(): Single<ForecastResponse> {

        return service.getCurrentForecast("","",1)
    }
}