package com.example.bachelorsapp.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun InputMaxHeight(onNumberEntered: (Double) -> Unit) {
    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .wrapContentHeight() // Adjust height based on content
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Vertical size of the shade") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { onNumberEntered(text.toDoubleOrNull() ?: 0.0) }) {
            Text("Submit")
        }

        Spacer(modifier = Modifier.height(18.dp))

    }
}