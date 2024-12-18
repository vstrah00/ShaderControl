package com.example.bachelorsapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainScreenFun(
    onNavigateToScreen1: () -> Unit,
    onNavigateToScreen2: () -> Unit,
    onNavigateToScreen3: () -> Unit
){
    Surface(

        modifier = Modifier.fillMaxSize()
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Button(
                onClick = onNavigateToScreen1,
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier,

                ) {
                Text(
                    modifier = Modifier
                        .padding(5.dp),
                    fontSize = 20.sp,
                    text = "Automatic",
                )
            }


            Button(
                onClick = onNavigateToScreen2,
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)

            ) {
                Text(
                    modifier = Modifier
                        .padding(5.dp),
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onSecondary,
                    text = "Manual",
                )
            }


            Button(
                onClick = onNavigateToScreen3,
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)

            ) {
                Text(
                    modifier = Modifier
                        .padding(5.dp),
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onSecondary,
                    text = "Setup",
                )
            }
        }
    }
}
