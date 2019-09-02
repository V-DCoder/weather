package com.apps.demo.apixuweather.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import io.reactivex.subjects.PublishSubject


class LocationManager(val context: Context) {

    private var fusedLocationClient: FusedLocationProviderClient? = null

    var locationPublisher: PublishSubject<LocationNotification> = PublishSubject.create()

    init {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    }

    @SuppressLint("MissingPermission")
    public fun getLocation(): PublishSubject<LocationNotification> {
        fusedLocationClient?.lastLocation?.addOnSuccessListener { location ->
            getAddressForLocation(location)

        }?.addOnFailureListener {
            locationPublisher.onNext(LocationNotification(null, Exception("Failed to find location")))
        }

        return locationPublisher
    }

    fun getAddressForLocation(location: Location?) {
        try {
            var address: Address? = null
            location?.run {
                val result = Geocoder(context).getFromLocation(this.latitude, this.longitude, 1)
                if (result != null && result.size > 0) {
                    address = result[0]
                }
            }

            locationPublisher.onNext(LocationNotification(Pair(location, address), null))
        } catch (e: java.lang.Exception) {
            locationPublisher.onNext(LocationNotification(null, Exception("Failed to find location")))
        }
    }

    data class LocationNotification(var location: Pair<Location?, Address?>?, var error: Exception?)

}