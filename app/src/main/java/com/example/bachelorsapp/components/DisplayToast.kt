package com.example.bachelorsapp.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun DisplayToast(string: String, showToast:Boolean, returnToast: (Boolean) -> Unit ){
    if(showToast) {
        Snackbar(
            modifier = Modifier.padding(16.dp),
            actionOnNewLine = true,
            dismissAction = {
                LaunchedEffect(showToast) {
                    if (showToast) {
                        delay(2000) // Wait for 2 seconds
                        returnToast(false) // Dismiss Snackbar after 2 seconds
                    }
                }
            }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp), // Occupy entire width
                contentAlignment = Alignment.BottomCenter // Align content to the bottom
            ) {
                Text(
                    text = string,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}