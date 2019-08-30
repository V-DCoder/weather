package com.apps.demo.apixuweather.viewmodel

import android.location.Address
import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apps.demo.apixuweather.location.LocationManager
import com.apps.demo.apixuweather.model.ForecastResponse
import com.apps.demo.apixuweather.repository.weatherAPIRepo.ForecastRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TodaysForecastViewModel @Inject constructor(
    private val locationManager: LocationManager,
    val repository: ForecastRepository
) : ViewModel() {

    private var compositeDisposable = CompositeDisposable()
    private var errorFound: MutableLiveData<Boolean> = MutableLiveData()
    private var showProgress: MutableLiveData<Boolean> = MutableLiveData()
    private var locationForecast: MutableLiveData<Pair<ForecastResponse?, Address?>> = MutableLiveData()
    private var address: Address? = null

    fun getLocationForecastLiveData(): MutableLiveData<Pair<ForecastResponse?, Address?>> {
        return locationForecast
    }

    fun getProgressDialogLiveData(): LiveData<Boolean> {
        return showProgress
    }

    fun getErrorLiveData(): LiveData<Boolean> {
        return errorFound
    }

    fun fetchForecast() {
        if (forecastNotAvailable()) {
            fetchNewForecast()
        }

    }

    private fun fetchNewForecast() {
        showProgress.postValue(true)
        compositeDisposable.add(locationManager.getLocation().subscribe({
            onLocationFound(it)
        }, {
            locationFetchFailed()
        }))
    }

    private fun forecastNotAvailable(): Boolean {
        return locationForecast.value == null || locationForecast.value?.first == null
    }

    private fun locationFetchFailed() {
        showProgress.postValue(false)
        errorFound.postValue(true)
    }

    private fun onLocationFound(locationPair: Pair<Location?, Address?>) {
        address = locationPair.second
        fetchForecastForLocation(locationPair.first)
    }

    private fun fetchForecastForLocation(location: Location?) {

        compositeDisposable.add(
            repository.getTodaysForecast("${location?.latitude},${location?.longitude}")
                .subscribe({ response: ForecastResponse ->
                    locationForecast.postValue(Pair(response, address))
                    showProgress.postValue(false)

                }, {
                    errorFound.postValue(true)
                    showProgress.postValue(false)
                    it.printStackTrace()
                })
        )
    }
}