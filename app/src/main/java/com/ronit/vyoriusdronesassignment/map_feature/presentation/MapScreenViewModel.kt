package com.ronit.vyoriusdronesassignment.map_feature.presentation


import android.Manifest
import android.annotation.SuppressLint
import android.location.Location
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import com.mapbox.geojson.Point
import com.ronit.vyoriusdronesassignment.map_feature.data.repository.MapFeatureRepositoryImpl
import com.ronit.vyoriusdronesassignment.map_feature.domain.dto.PointerInfoDto
import com.ronit.vyoriusdronesassignment.map_feature.presentation.event.MapScreenEvent
import com.ronit.vyoriusdronesassignment.map_feature.presentation.state.MapScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapScreenViewModel @Inject constructor(
        private val repositoryImpl: MapFeatureRepositoryImpl
):ViewModel() {


    private val permissionList = listOf(

            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.INTERNET
    )

    var state by mutableStateOf(MapScreenState())
        private set

    var currentUserLocation by mutableStateOf<Point?>(null)


    var askPermission by mutableStateOf(false)
        private set
//
//    var currentPointer by mutableStateOf<Point?>(null)
//        private set
//
//    var  currentPointerDetails by mutableStateOf<PointerInfoDto?>(null)
//        private set



    fun onEvent(event: MapScreenEvent){

        when(event){
            is MapScreenEvent.UpdateCurrentLocation -> {

                updateCurrentLocation(event.location)
            }

            MapScreenEvent.OnChangeMapTypeIconClicked -> {

                state=state.copy(mapTypeBottomSheetVisible = true)
            }

            MapScreenEvent.OnDismissingMapTypeBottomSheet -> {

                state=state.copy(mapTypeBottomSheetVisible = false)

            }

            is MapScreenEvent.OnMapStyleChange -> {
                if(state.mapStyle!=event.style)
                    state=state.copy(mapStyle = event.style)
            }

            is MapScreenEvent.OnMapClicked -> {

                viewModelScope.launch {


                   val  currentPointerDetails=repositoryImpl.getPointerInfo(
                            latitude = event.point.latitude().toString(),
                            longitude = event.point.longitude().toString()
                    )

                    state=state.copy(currentPointerDetails=currentPointerDetails, currentPointer = event.point)
                    event.moveMap()



                }.invokeOnCompletion {

                    state=state.copy(showPointerInfoBottomSheet = true)

                }

            }

            MapScreenEvent.OnPointerBottomSheetDismissed ->{
                state=state.copy(showPointerInfoBottomSheet = false)
                state=state.copy(
                        currentPointer = null,
                        currentPointerDetails = null,
                )
            }

            MapScreenEvent.AskPermission -> {

                askPermission=!askPermission
            }
        }
    }


    @SuppressLint("MissingPermission")
    @OptIn(ExperimentalPermissionsApi::class)
    private fun getCurrentLocation(
        priority: Boolean=true,
        permissionState: MultiplePermissionsState,
        fusedLocationProviderClient: FusedLocationProviderClient,
        onGetCurrentLocationSuccess:(Pair<Double,Double>)->Unit,
        onGetCurrentLocationFailure:(Exception)->Unit
    ){

        val accuracy = if(priority) Priority.PRIORITY_HIGH_ACCURACY else Priority.PRIORITY_BALANCED_POWER_ACCURACY


        if(permissionState.allPermissionsGranted){

            fusedLocationProviderClient.getCurrentLocation(
                    accuracy,CancellationTokenSource().token
            ).addOnSuccessListener {

                it?.let {
                    onGetCurrentLocationSuccess(Pair(it.latitude,it.altitude))
                }
            }.addOnFailureListener {
                    onGetCurrentLocationFailure(it)
                }
        }
    }


    private fun updateCurrentLocation(location: Location){


       currentUserLocation= Point.fromLngLat(location.longitude,location.latitude)
    }








}