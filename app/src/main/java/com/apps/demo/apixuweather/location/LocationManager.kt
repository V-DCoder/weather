package com.apps.demo.apixuweather.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import android.location.Location
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import io.reactivex.subjects.PublishSubject
import java.util.*


class LocationManager(val context: Context) {

    private var fusedLocationClient: FusedLocationProviderClient? = null

    private var locationPublisher: PublishSubject<Location?> = PublishSubject.create()

    init {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    }

    @SuppressLint("MissingPermission")
    public fun getLocation() : PublishSubject<Location?> {
        fusedLocationClient?.lastLocation?.addOnSuccessListener { location ->

            var result = Geocoder(context).getFromLocation(location.latitude,location.longitude,1)

            locationPublisher.onNext(location)
        }?.addOnFailureListener {
            Log.w("Location", "" + it.localizedMessage + it.message)
            locationPublisher.onError(Exception("Failed to find location"))
        }

        return locationPublisher
    }
}