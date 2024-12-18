package com.example.bachelorsapp.data

import kotlinx.serialization.Serializable

@Serializable
data class PostData(
    val lat: Double,
    val lon: Double,
    val azimuth: Double
)

@Serializable
data class PostDataMaxSunlight(
    val maxSunlight: Double
)
@Serializable
data class PostDataMaxHeight(
    val maxHeight: Double
)

@Serializable
data class PostDataAutoFlag(
    val autoFlag: Boolean
)

@Serializable
data class PostDataManualDistance(
    val manualDistance: Double
)
