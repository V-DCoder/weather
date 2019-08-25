package com.apps.demo.apixuweather.viewmodel

import com.apps.demo.apixuweather.location.LocationManager
import com.apps.demo.apixuweather.repository.weatherAPIRepo.ForecastRepository
import javax.inject.Inject

class TodaysForecastViewModel @Inject constructor(
    private val locationManager: LocationManager,
    val repository: ForecastRepository
) {

    public fun getLocation() {
        locationManager.getLocation()
    }
}