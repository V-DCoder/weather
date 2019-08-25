package com.apps.demo.apixuweather.location

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


class LocationManager(context: Context) {

    private var fusedLocationClient: FusedLocationProviderClient? = null

    init {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    }

    @SuppressLint("MissingPermission")
    public fun getLocation() {

        fusedLocationClient?.lastLocation?.addOnSuccessListener { location ->

            Log.w("Location", "" + location.latitude + "   " + location.longitude)
        }?.addOnFailureListener {
            Log.w("Location", "" + it.localizedMessage + it.message)

        }
    }
}