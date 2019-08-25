package com.apps.demo.apixuweather.repository

import com.apps.demo.apixuweather.repository.weatherAPIRepo.ForecastRepository

interface Repository {
    val forecastRepository: ForecastRepository
}