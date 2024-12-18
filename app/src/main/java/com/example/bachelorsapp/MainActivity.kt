package com.example.bachelorsapp

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.bachelorsapp.data.MyData
import com.example.bachelorsapp.ui.theme.BachelorsAppTheme
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Slider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.bachelorsapp.functionality.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bachelorsapp.screens.*


const val LOCATION_PERMISSION_REQUEST_CODE = 1001


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        requestLocationPermission(this)
        setContent {
            MyApp(this)
        }
    }
}


@Composable
fun MyApp(activity: Activity) {
    val navController = rememberNavController()
    val view = LocalView.current

    // Apply the function to hide the system UI
    hideSystemUI(view)

    NavHost(navController, startDestination = "home") {
        composable("home") {
            MainScreenFun(
                onNavigateToScreen1 = { navController.navigate("screen1") },
                onNavigateToScreen2 = { navController.navigate("screen2") },
                onNavigateToScreen3 = { navController.navigate("screen3") }
            )
        }
        composable("screen1") {
            AutomaticSet()
        }
        composable("screen2") {
            ManualSet(
               // navigateToHome = { navController.navigate("home") } // Navigate back to home
            )        }
        composable("screen3") {
            AppSetup(activity)
        }
    }
}
