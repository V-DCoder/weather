package com.apps.demo.apixuweather.repository.weatherAPIRepo

import com.apps.demo.apixuweather.model.ForecastResponse
import com.apps.demo.apixuweather.utils.APIXUService
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject


class ForecastRepositoryImpl
@Inject constructor(
    private val service: APIXUService, private val subscriberOn: Scheduler,
    private val observerOn: Scheduler
) : ForecastRepository {


    override fun getTodaysForecast(latLong: String): Single<ForecastResponse> {
        return service.getCurrentForecast(latLong).subscribeOn(subscriberOn).observeOn(observerOn)
    }
}