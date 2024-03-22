package com.ronit.vyoriusdronesassignment.map_feature.presentation

import android.Manifest
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import com.mapbox.geojson.Point
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.dsl.cameraOptions
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.ronit.vyoriusdronesassignment.map_feature.presentation.components.MapOverLay
import com.ronit.vyoriusdronesassignment.map_feature.presentation.components.MapTypeBottomSheet
import com.ronit.vyoriusdronesassignment.map_feature.presentation.components.PointerBottomSheet
import com.ronit.vyoriusdronesassignment.map_feature.presentation.event.MapScreenEvent
import com.ronit.vyoriusdronesassignment.map_feature.presentation.utility.Constants


@SuppressLint("MissingPermission")
@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class,
       MapboxExperimental::class
)
@Composable
fun MainScreen(
    fusedLocationProviderClient: FusedLocationProviderClient?
) {


    val viewModel : MapScreenViewModel = hiltViewModel()
    val cameraPosition:Point = viewModel.currentUserLocation ?: Constants.DEFAULT_CAMERA_POSITION_POINT

    val mapViewportState  = rememberMapViewportState{
        setCameraOptions {
            zoom(2.0)
            center(cameraPosition)
            pitch(0.0)
            bearing(0.0)
        }
    }




    val permissionState= rememberMultiplePermissionsState(
            permissions = listOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.INTERNET
            )

    )



    LaunchedEffect(key1 = Unit) {

        if (permissionState.revokedPermissions.isNotEmpty()) {
            permissionState.launchMultiplePermissionRequest()
        } else {
            fusedLocationProviderClient!!.getCurrentLocation(
                    Priority.PRIORITY_HIGH_ACCURACY,
                    CancellationTokenSource().token
            )
                .addOnSuccessListener {

                    Log.d("Loc", "Long :- ${it.longitude}  ${it.latitude}")
                    viewModel.onEvent(MapScreenEvent.UpdateCurrentLocation(it))

                    mapViewportState.easeTo(

                                    cameraOptions = cameraOptions {
                                        this.center(Point.fromLngLat(it.longitude,it.latitude))
                                        zoom(7.0)

                                    }

                    )

                }
        }
    }




    val mapTypeBottomSheetState = rememberModalBottomSheetState()







    val pointerBottomSheetState = rememberModalBottomSheetState()

    Scaffold(
            modifier = Modifier
                .fillMaxSize(),


    ) {

        Box(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
        ) {

            if(viewModel.state.mapTypeBottomSheetVisible){
                MapTypeBottomSheet(viewModel,mapTypeBottomSheetState)
            }
            
            
            if(viewModel.state.showPointerInfoBottomSheet){
                PointerBottomSheet(
                        viewmodel = viewModel,
                        sheetState = pointerBottomSheetState
                ){
                    viewModel.onEvent(MapScreenEvent.OnPointerBottomSheetDismissed)
                }
            }




            val style = remember {
                viewModel.state.mapStyle
            }
            MapScreen(
                    modifier = Modifier,
                     style= style,
                    viewModel= viewModel,
                    mapViewportState=mapViewportState
            )

            MapOverLay(viewModel,pointerBottomSheetState){

                mapViewportState.flyTo(
                        cameraOptions = cameraOptions {
                            this.center(viewModel.state.currentPointer)
                            zoom(7.0)

                        }
                )
            }

        }
    }





}


@Preview(showBackground = true)
@Composable
fun Preview() {
    MainScreen( fusedLocationProviderClient =null )
}