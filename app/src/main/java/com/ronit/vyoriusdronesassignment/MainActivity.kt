package com.ronit.vyoriusdronesassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.mapbox.common.MapboxOptions
import com.ronit.vyoriusdronesassignment.map_feature.presentation.MainScreen
import com.ronit.vyoriusdronesassignment.ui.theme.VyoriusDronesAssignmentTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalPermissionsApi::class)

    private lateinit var  fusedLocationProviderClient: FusedLocationProviderClient
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MapboxOptions.accessToken = BuildConfig.MAP_API_KEY
        fusedLocationProviderClient=LocationServices.getFusedLocationProviderClient(this)

        setContent {
            VyoriusDronesAssignmentTheme {
                // A surface container using the 'background' color from the theme


                    MainScreen(
                           fusedLocationProviderClient=fusedLocationProviderClient
                    )


            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
            text = "Hello $name!",
            modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VyoriusDronesAssignmentTheme {
        Greeting("Android")
    }
}