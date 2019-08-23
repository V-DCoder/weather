package com.apps.demo.apixuweather.location

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


class LocationManager(application: Application) {

    private var fusedLocationClient: FusedLocationProviderClient? = null

    init {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(application)
        getLocation()
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {

        fusedLocationClient?.lastLocation?.addOnSuccessListener { location ->

            Log.w("Location", "" + location.latitude + "   " + location.longitude)
        }?.addOnFailureListener {
            Log.w("Location", "" + it.localizedMessage + it.message)

        }
    }
}