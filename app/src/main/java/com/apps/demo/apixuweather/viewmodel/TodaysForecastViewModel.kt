package com.apps.demo.apixuweather.viewmodel

import android.location.Location
import android.util.Log
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
    private var locationForecast: MutableLiveData<ForecastResponse> = MutableLiveData()

    fun getLocationForecastLiveData(): LiveData<ForecastResponse> {
        return locationForecast
    }

    fun getProgressDialogLiveData(): LiveData<Boolean> {
        return showProgress
    }

    fun getErrorLiveData(): LiveData<Boolean> {
        return errorFound
    }

    fun fetchForecast() {
        compositeDisposable.add(locationManager.getLocation().subscribe({
            onLocationFound(it)
        }, {
            locationFetchFailed()
        }))
    }

    private fun locationFetchFailed() {

    }

    private fun onLocationFound(location: Location?) {

        fetchForecastForLocation(location)
    }

    private fun fetchForecastForLocation(location: Location?) {
//        ${location?.latitude},${location?.longitude}
        compositeDisposable.add(
            repository.getTodaysForecast("19.0990,73.0098").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response: ForecastResponse ->
                    Log.w("response", response.location.name)

                }, {
                    Log.w("response", it.message)
                    it.printStackTrace()
                })
        )
    }
}