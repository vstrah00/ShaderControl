package com.example.bachelorsapp.components
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import com.example.bachelorsapp.functionality.performGetReq
import com.example.bachelorsapp.functionality.performPostReqAutoFlag
import com.example.bachelorsapp.functionality.performPostReqManualDistance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun VerticalSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    sliderHeight: Int = 200,
    sliderWidth: Int = 8
) {
    var sliderSize by remember { mutableFloatStateOf(3.0f) }
    var sliderPosition by remember { mutableFloatStateOf(value) }

    val valueRange by remember(sliderSize) {
        mutableStateOf(0.0f..sliderSize)
    }

    LaunchedEffect(true) {
        performGetReq { receivedData ->
            sliderSize = receivedData.maxHeight.toFloat()
            sliderPosition = receivedData.manualDistance.toFloat()
        }
    }

    Column(
        modifier = modifier.fillMaxHeight()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
        ) {
            Slider(
                value = sliderPosition,
                onValueChange = { newValue ->
                    sliderPosition = newValue
                    onValueChange(newValue)
                },
                onValueChangeFinished = {
                    CoroutineScope(Dispatchers.IO).launch {
                        performPostReqManualDistance(sliderPosition.toDouble()) {
                            // Handle response if needed
                        }
                    }
                },
                valueRange = valueRange,
                modifier = Modifier
                    .rotate(90f)
                    .fillMaxWidth()
                    .align(Alignment.Center)
            )
            Text("Value: $sliderPosition")
        }
    }
}

