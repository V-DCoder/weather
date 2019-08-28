package com.apps.demo.apixuweather.utils

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat

object PermissionUtil {

    fun checkApplicationHasLocationPermission(application: Application): Boolean {
        return ActivityCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }
}