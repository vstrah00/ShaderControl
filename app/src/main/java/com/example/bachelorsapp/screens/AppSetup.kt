package com.example.bachelorsapp.screens

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.bachelorsapp.components.*
import com.example.bachelorsapp.data.MyData
import com.example.bachelorsapp.functionality.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun AppSetup(activity: Activity) {
    val context = LocalContext.current
    var showToast by remember { mutableStateOf(false) }

    var latitude by remember { mutableDoubleStateOf(0.0) }
    var longitude by remember { mutableDoubleStateOf(0.0) }
    var azimuth by remember { mutableFloatStateOf(-1.0f) }
    var maxHeight by remember { mutableDoubleStateOf(0.0) }
    var apiData by remember { mutableStateOf<MyData?>(null) } // Mutable state to hold received API data

    Surface (
        modifier = Modifier.fillMaxSize()
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {

            Box(
                modifier = Modifier.fillMaxWidth(), // Occupy entire width
                contentAlignment = Alignment.Center // Center content
            ){
                InputMaxHeight { newMaxHeight ->
                    maxHeight = newMaxHeight
                    // Launch a coroutine to perform the network request
                    CoroutineScope(Dispatchers.IO).launch {
                        performPostReqMaxHeight(maxHeight){receivedData->
                            if(receivedData.maxHeight == maxHeight){
                                showToast = true
                            }

                        }
                    }
                }
            }

            Button(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = {
                    if (ContextCompat.checkSelfPermission(
                            context,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        // Get location and azimuth
                        getAzimuth(context) { azimuthValue ->
                            azimuth = azimuthValue
                        }
                        getLocation(context) { lat, long ->
                            latitude = lat
                            longitude = long

                            // Launch a coroutine to perform the network request
                            CoroutineScope(Dispatchers.IO).launch {
                                performPostReq(
                                    latitude,
                                    longitude,
                                    azimuth.toDouble()
                                ) { receivedData ->
                                    apiData = receivedData
                                }
                            }
                        }

                    } else {
                        // Location permission is not granted
                        requestLocationPermission(activity)
                    }

                }) {
                Text("Post Location and Azimuth")
            }

            Text(text = "Please point your phone perpendicularly at the shade before pressing this button :)",
                textAlign = TextAlign.Center)

        }


        if (apiData != null) {
            Box(
                modifier = Modifier.fillMaxWidth().padding(15.dp), // Occupy entire width
                contentAlignment = Alignment.BottomCenter // Align content to the bottom
            ) {
                // Display API data if available
                apiData?.let { data ->
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Received API Confirmation:")
                        Text("Lat: ${data.latitude}")
                        Text("Lon: ${data.longitude}")
                        Text("Measured Angle: ${data.measuredAngle}")
                        Text("Maximum Height: ${data.maxHeight}")
                        // Add more Text composable items for other data fields as needed
                    }
                }
            }

        }

    }

    DisplayToast(
        string = "$maxHeight is set as your shades size",
        showToast = showToast
    ) {returnToast ->
        showToast = returnToast
    }

}
