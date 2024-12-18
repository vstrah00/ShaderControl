package com.example.bachelorsapp.data

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class MyData(
    @SerialName("lat") val latitude: Double,
    @SerialName("lon") val longitude: Double,
    @SerialName("measuredAngle") val measuredAngle: Double,
    @SerialName("maxHeight") val maxHeight: Double,
    @SerialName("maxSunlight") val maxSunlight: Double,
    @SerialName("timestamp") val timestamp: Double,
    @SerialName("azimuth") val azimuth: Double,
    @SerialName("elevation") val elevation: Double,
    @SerialName("deltaHeight") val deltaHeight: Double,
    @SerialName("sunFlag") val sunFlag: String,
    @SerialName("distance") val distance: Double,
    @SerialName("autoFlag") val autoFlag: Boolean,
    @SerialName("manualDistance") val manualDistance: Double,
)
