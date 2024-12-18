package com.example.bachelorsapp.functionality

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.compose.runtime.Composable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("MissingPermission")
fun getLocation(context: Context, callback: (Double, Double) -> Unit) {
    try {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, object : LocationListener {
            override fun onLocationChanged(location: Location) {
                callback(location.latitude, location.longitude)
                Log.e("getLocation", "Error getting location: ${location.latitude}")
            }

            @Deprecated("Deprecated in Java")
            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}

            override fun onProviderEnabled(provider: String) {}

            override fun onProviderDisabled(provider: String) {}
        }, null)
    } catch (e: Exception) {
        Log.e("getLocation", "Error getting location: ${e.message}")
    }
}


fun getAzimuth(context: Context, callback: (Float) -> Unit) {
    try {
        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val rotationVectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)

        if (rotationVectorSensor != null) {
            val sensorEventListener = object : SensorEventListener {
                override fun onSensorChanged(event: SensorEvent?) {
                    event?.let {
                        if (event.sensor.type == Sensor.TYPE_ROTATION_VECTOR) {
                            val rotationMatrix = FloatArray(9)
                            SensorManager.getRotationMatrixFromVector(rotationMatrix, event.values)
                            val orientation = FloatArray(3)
                            SensorManager.getOrientation(rotationMatrix, orientation)
                            val azimuthRadians = orientation[0]
                            var azimuthDegrees = Math.toDegrees(azimuthRadians.toDouble()).toFloat()
                            azimuthDegrees = (azimuthDegrees + 360) % 360 // Ensure azimuth is within [0, 360] range
                            callback(azimuthDegrees)
                            // Unregister the sensor listener after obtaining the azimuth value
                            sensorManager.unregisterListener(this)
                        }
                    }
                }

                override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
                    if (sensor == rotationVectorSensor) {
                        Log.d("getAzimuth", "Accuracy changed: $accuracy")
                    }
                }
            }

            sensorManager.registerListener(sensorEventListener, rotationVectorSensor, SensorManager.SENSOR_DELAY_NORMAL)
        } else {
            Log.e("getAzimuth", "Rotation vector sensor not available")
            // Handle sensor not available
            callback(0f) // Notify the caller with a default value
        }
    } catch (e: Exception) {
        Log.e("getAzimuth", "Error getting azimuth: ${e.message}")
        // Handle other exceptions
        callback(0f) // Notify the caller with a default value
    }
}
