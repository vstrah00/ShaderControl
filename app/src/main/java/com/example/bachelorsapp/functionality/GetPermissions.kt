package com.example.bachelorsapp.functionality

import android.Manifest
import android.app.Activity
import androidx.core.app.ActivityCompat
import com.example.bachelorsapp.LOCATION_PERMISSION_REQUEST_CODE

fun requestLocationPermission(activity: Activity) {
    ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)

}
