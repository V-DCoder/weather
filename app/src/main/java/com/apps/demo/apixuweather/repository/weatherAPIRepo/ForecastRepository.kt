package com.apps.demo.apixuweather.repository.weatherAPIRepo

import com.gojeck.apps.whertherly.model.ForecastResponse
import io.reactivex.Single

interface ForecastRepository {

    fun getTodaysForecast(): Single<ForecastResponse>
}