package com.example.bachelorsapp.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bachelorsapp.components.DisplayToast
import com.example.bachelorsapp.data.MyData
import com.example.bachelorsapp.functionality.performPostReqAutoFlag
import com.example.bachelorsapp.functionality.performPostReqMaxSunlight
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AutomaticSet() {
    var maxSunlight by remember { mutableFloatStateOf(-1.0f) }
    var showToast by remember { mutableStateOf(false) }
    var apiData by remember { mutableStateOf<MyData?>(null) }

    LaunchedEffect (true){
        performPostReqAutoFlag(true){receivedData ->
            if(receivedData.autoFlag){
                showToast=true
                maxSunlight = receivedData.maxSunlight.toFloat()
            }
        }
    }

    Surface (
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Text("How much sunlight would you like?")
                Slider(
                    value = maxSunlight,
                    onValueChange = { maxSunlight = it },
                    valueRange = 0.01f..5.0f,
                    steps = 100,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                Button(onClick = {
                    if (maxSunlight > 0) {
                        CoroutineScope(Dispatchers.IO).launch {
                            performPostReqMaxSunlight(maxSunlight.toDouble()) { receivedData ->
                                apiData = receivedData
                            }
                        }

                    }

                }) {
                    Text("Apply")
                }

            }
        }
    }

    DisplayToast(
        string = "Automatic configuration is going to be used",
        showToast = showToast
    ) {returnToast ->
        showToast = returnToast
    }


}
