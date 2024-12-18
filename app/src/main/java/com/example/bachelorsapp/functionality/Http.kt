package com.example.bachelorsapp.functionality

import com.example.bachelorsapp.data.MyData
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.json.*
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import io.ktor.client.features.json.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import android.util.Log
import io.ktor.client.features.get
import io.ktor.client.request.post
import kotlinx.serialization.decodeFromString
import com.example.bachelorsapp.data.PostData
import com.example.bachelorsapp.data.PostDataAutoFlag
import com.example.bachelorsapp.data.PostDataManualDistance
import com.example.bachelorsapp.data.PostDataMaxHeight
import com.example.bachelorsapp.data.PostDataMaxSunlight


private const val API_URL = "https://vitostrah.pythonanywhere.com/"

val json = Json

val client = HttpClient(OkHttp) {
    install(JsonFeature) {
        serializer = KotlinxSerializer(json)
    }
}

suspend fun performGetReq(callback: (MyData) -> Unit) {
    try {
        Log.d("performGetReq", "Sending GET request to $API_URL")
        val response: String = client.get(API_URL)
        Log.d("performGetReq", "Received response: $response")

        // Deserializing JSON response using Kotlinx Serialization
        val myData = json.decodeFromString<MyData>(response)

        Log.d("performGetReq", "Parsed MyData: $myData")
        withContext(Dispatchers.Main) {
            Log.d("performGetReq", "Invoking callback with MyData: $myData")
            callback(myData)
        }
    } catch (e: Exception) {
        Log.e("performGetReq", "Error: ${e.message}", e)
    }
}

suspend fun performPostReq(lat: Double, lon: Double, azimuth: Double, callback: (MyData) -> Unit) {
    try {
        Log.d("performPostReq", "Sending POST request to $API_URL, azimuth:$azimuth")

        // Create an instance of PostData
        val postData = PostData(lat, lon, azimuth)

        // Log the POST request URL
        val postUrl = buildString {
            append(API_URL)
            append("?lat=$lat&lon=$lon&measuredAngle=$azimuth")
        }
        Log.d("performPostReq", "POST request URL: $postUrl")

        // Send the POST request with the data in the request body
        val response: String = client.post(postUrl) {
            body = json.encodeToString(PostData.serializer(), postData)
        }

        Log.d("performPostReq", "Received response: $response")

        // Deserialize the JSON response using Kotlinx Serialization
        val myData = json.decodeFromString<MyData>(response)

        Log.d("performPostReq", "Parsed MyData: $myData")
        withContext(Dispatchers.Main) {
            Log.d("performPostReq", "Invoking callback with MyData: $myData")
            callback(myData)
        }
    } catch (e: Exception) {
        Log.e("performPostReq", "Error: ${e.message}", e)
    }
}








suspend fun performPostReqMaxSunlight(maxSunlight: Double, callback: (MyData) -> Unit) {
    try {

        val postData = PostDataMaxSunlight(maxSunlight)

        // Send the POST request with the data in the request body
        val response: String = client.post(API_URL) {
            body = json.encodeToString(PostDataMaxSunlight.serializer(), postData)
        }

        Log.d("performPostReqMaxSunlight", "Received response: $response")

        // Deserialize the JSON response using Kotlinx Serialization
        val myData = json.decodeFromString<MyData>(response)

        Log.d("performPostReqMaxSunlight", "Parsed MyData: $myData")
        withContext(Dispatchers.Main) {
            Log.d("performPostReqMaxSunlight", "Invoking callback with MyData: $myData")
            callback(myData)
        }
    } catch (e: Exception) {
        Log.e("performPostReqMaxSunlight", "Error: ${e.message}", e)
    }
}

suspend fun performPostReqMaxHeight(maxHeight: Double, callback: (MyData) -> Unit) {
    try {

        val postData = PostDataMaxHeight(maxHeight)
        // Log the POST request URL
        val postUrl = buildString {
            append(API_URL)
            append("?maxHeight=$maxHeight")
        }
        Log.d("performPostReq2", "POST request URL: $postUrl")


        // Send the POST request with the data in the request body
        val response: String = client.post(postUrl) {
            body = json.encodeToString(PostDataMaxHeight.serializer(), postData)
        }

        Log.d("performPostReq", "Received response: $response")

        // Deserialize the JSON response using Kotlinx Serialization
        val myData = json.decodeFromString<MyData>(response)

        Log.d("performPostReq", "Parsed MyData: $myData")
        withContext(Dispatchers.Main) {
            Log.d("performPostReq", "Invoking callback with MyData: $myData")
            callback(myData)
        }
    } catch (e: Exception) {
        Log.e("performPostReq", "Error: ${e.message}", e)
    }
}


suspend fun performPostReqAutoFlag(autoFlag: Boolean, callback: (MyData) -> Unit) {
    try {

        val postData = PostDataAutoFlag(autoFlag)
        // Log the POST request URL
        val postUrl = buildString {
            append(API_URL)
            append("?autoFlag=$autoFlag")
        }
        Log.d("performPostReq2", "POST request URL: $postUrl")


        // Send the POST request with the data in the request body
        val response: String = client.post(postUrl) {
            body = json.encodeToString(PostDataAutoFlag.serializer(), postData)
        }

        Log.d("performPostReq", "Received response: $response")

        // Deserialize the JSON response using Kotlinx Serialization
        val myData = json.decodeFromString<MyData>(response)

        Log.d("performPostReq", "Parsed MyData: $myData")
        withContext(Dispatchers.Main) {
            Log.d("performPostReq", "Invoking callback with MyData: $myData")
            callback(myData)
        }
    } catch (e: Exception) {
        Log.e("performPostReq", "Error: ${e.message}", e)
    }
}



suspend fun performPostReqManualDistance(manualDistance: Double, callback: (MyData) -> Unit) {
    try {

        val postData = PostDataManualDistance(manualDistance)
        // Log the POST request URL
        val postUrl = buildString {
            append(API_URL)
            append("?manualDistance=$manualDistance")
        }
        Log.d("performPostReq2", "POST request URL: $postUrl")


        // Send the POST request with the data in the request body
        val response: String = client.post(postUrl) {
            body = json.encodeToString(PostDataManualDistance.serializer(), postData)
        }

        Log.d("performPostReq", "Received response: $response")

        // Deserialize the JSON response using Kotlinx Serialization
        val myData = json.decodeFromString<MyData>(response)

        Log.d("performPostReq", "Parsed MyData: $myData")
        withContext(Dispatchers.Main) {
            Log.d("performPostReq", "Invoking callback with MyData: $myData")
            callback(myData)
        }
    } catch (e: Exception) {
        Log.e("performPostReq", "Error: ${e.message}", e)
    }
}
