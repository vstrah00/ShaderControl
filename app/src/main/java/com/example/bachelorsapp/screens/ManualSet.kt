package com.example.bachelorsapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Snackbar
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.bachelorsapp.components.*
import com.example.bachelorsapp.functionality.performPostReqAutoFlag
import kotlinx.coroutines.delay

@Composable
fun ManualSet(){
    var showToast by remember { mutableStateOf(false) }
    var sliderPosition by remember { mutableFloatStateOf(0f) }

    LaunchedEffect (true){
        performPostReqAutoFlag(false){receivedData ->
            if(!receivedData.autoFlag){
                showToast=true
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
            VerticalSlider(
                value = sliderPosition,
                onValueChange = { newValue ->
                    sliderPosition = newValue
                }
            )
        }
    }




    DisplayToast(
        string = "Manual configuration is going to be used",
        showToast = showToast
    ) {returnToast ->
        showToast = returnToast
    }



}