package com.apps.demo.apixuweather

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.apps.demo.apixuweather.location.LocationManager
import com.apps.demo.apixuweather.repository.Repository
import com.apps.demo.apixuweather.repository.weatherAPIRepo.ForecastRepository
import com.apps.demo.apixuweather.viewmodel.TodaysForecastViewModel
import com.gojeck.apps.whertherly.model.ForecastResponse
import dagger.android.AndroidInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class MainActivity : AppCompatActivity() {


    @Inject
    lateinit var locationManager: LocationManager

    @Inject
    lateinit var forecaRepository: ForecastRepository

    lateinit var todaysForecastViewModel: TodaysForecastViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkLocationPermission()

//        todaysForecastViewModel = TodaysForecastViewModel(locationManager,forecaRepository)

        locationManager.getLocation()

        forecaRepository.getTodaysForecast().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                response:ForecastResponse ->
                Log.w("response",response.location.name)

            },{
                Log.w("response",it.message)
                it.printStackTrace()
            })

    }

    private fun checkLocationPermission() {
        if (
            ActivityCompat.checkSelfPermission(
                application,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
                2
            )


        } else {
            locationManager = LocationManager(application)
        }
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

                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
