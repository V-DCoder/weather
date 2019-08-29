package com.apps.demo.apixuweather.view

import android.Manifest
import android.content.pm.PackageManager
import android.location.Address
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.apps.demo.apixuweather.R
import com.apps.demo.apixuweather.model.ForecastResponse
import com.apps.demo.apixuweather.utils.PermissionUtil
import com.apps.demo.apixuweather.view.adapter.ForecastAdapter
import com.apps.demo.apixuweather.viewmodel.TodaysForecastViewModel
import com.apps.demo.apixuweather.viewmodel.ViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.error_layout.*
import kotlinx.android.synthetic.main.forecast_container.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    lateinit var todaysForecastViewModel: TodaysForecastViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {

        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todaysForecastViewModel = ViewModelProviders.of(
            this,
            viewModelFactory
        ).get(TodaysForecastViewModel::class.java)
        initObservers()
        retry.setOnClickListener { onRetry() }


        fetchForecastForCurrentLocation()
    }


    private fun initObservers() {
        todaysForecastViewModel.getErrorLiveData().observe(this, Observer { onErrorUI() })
        todaysForecastViewModel.getProgressDialogLiveData().observe(this, Observer { showProgressBar(it) })
        todaysForecastViewModel.getLocationForecastLiveData().observe(this, Observer { onForecastAvailable(it) })
    }


    private fun onForecastAvailable(forecastToday: Pair<ForecastResponse?, Address?>) {
        error_container.visibility = View.GONE
        progress.visibility = View.GONE
        currentTemperature.text = getString(R.string.current_temperature, forecastToday.first?.current?.tempC)
        currentCity.text = forecastToday.second?.locality
        val adapter = ForecastAdapter()
        adapter.forecastday = forecastToday.first?.forecast?.forecastday
        forecast.adapter = adapter
        animateRecyclerView()
    }

    private fun animateRecyclerView() {
        forecast.visibility = View.VISIBLE
        val slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up_animation)
        forecast.startAnimation(slideUp)

    }


    private fun showProgressBar(showDialog: Boolean?) {
        if (showDialog == true) {
            progress.visibility = View.VISIBLE
        } else {
            progress.visibility = View.GONE
        }
    }

    private fun onErrorUI() {
        error_container.visibility = View.VISIBLE
    }

    private fun onRetry() {
        error_container.visibility = View.GONE
        fetchForecastForCurrentLocation()
    }


    private fun fetchForecastForCurrentLocation() {
        if (
            PermissionUtil.checkApplicationHasLocationPermission(application)
        ) {
                todaysForecastViewModel.fetchForecast()
        } else {
            getLocationPermission()
        }
    }

    private fun getLocationPermission() {
        ActivityCompat.requestPermissions(
            this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
            1000
        )
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1000 -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    fetchForecastForCurrentLocation()
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                    onErrorUI()
                }
            }
        }
    }
}
