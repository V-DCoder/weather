package com.apps.demo.apixuweather.repository

import com.apps.demo.apixuweather.repository.weatherAPIRepo.ForecastRepository
import javax.inject.Inject

data class RepositoryImpl @Inject constructor(override val forecastRepository: ForecastRepository) : Repository