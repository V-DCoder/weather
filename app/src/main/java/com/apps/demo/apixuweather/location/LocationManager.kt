package com.apps.demo.apixuweather.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.util.Log
import com.google.android.gms.common.util.ArrayUtils
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import io.reactivex.subjects.PublishSubject
import java.util.*


class LocationManager(val context: Context) {

    private var fusedLocationClient: FusedLocationProviderClient? = null

    private var locationPublisher: PublishSubject<Pair<Location?,Address?>> = PublishSubject.create()

    init {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    }

    @SuppressLint("MissingPermission")
    public fun getLocation() : PublishSubject<Pair<Location?, Address?>> {
        fusedLocationClient?.lastLocation?.addOnSuccessListener { location ->
            locationPublisher.onNext(Pair(location, getAddressForLocation(location)))
        }?.addOnFailureListener {
            Log.w("Location", "" + it.localizedMessage + it.message)
            locationPublisher.onError(Exception("Failed to find location"))
        }

        return locationPublisher
    }

    fun getAddressForLocation(location : Location?) : Address?
    {
        var address : Address? = null
        location?.run{
            val result = Geocoder(context).getFromLocation(this.latitude,this.longitude,1)
            if(result!=null && result.size>0)
            {
                address = result[0]
            }
        }

        return address
    }
}