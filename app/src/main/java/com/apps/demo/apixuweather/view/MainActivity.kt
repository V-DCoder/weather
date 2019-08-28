package com.apps.demo.apixuweather.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProviders
import com.apps.demo.apixuweather.R
import com.apps.demo.apixuweather.utils.PermissionUtil
import com.apps.demo.apixuweather.viewmodel.TodaysForecastViewModel
import com.apps.demo.apixuweather.viewmodel.ViewModelFactory
import dagger.android.AndroidInjection
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
                }
            }
        }
    }
}
