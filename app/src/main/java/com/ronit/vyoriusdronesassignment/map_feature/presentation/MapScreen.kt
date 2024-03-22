package com.ronit.vyoriusdronesassignment.map_feature.presentation

import android.content.Context
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.mapbox.geojson.Point
import com.mapbox.maps.MapInitOptions
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.dsl.cameraOptions
import com.mapbox.maps.extension.compose.MapEffect
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.MapViewportState
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.mapbox.maps.extension.compose.annotation.generated.CircleAnnotation
import com.mapbox.maps.extension.compose.annotation.generated.PointAnnotation
import com.mapbox.maps.extension.style.style
import com.mapbox.maps.plugin.animation.MapAnimationOptions
import com.mapbox.maps.plugin.attribution.generated.AttributionSettings
import com.ronit.vyoriusdronesassignment.R
import com.ronit.vyoriusdronesassignment.map_feature.presentation.event.MapScreenEvent
import com.ronit.vyoriusdronesassignment.map_feature.presentation.utility.Constants


@OptIn(MapboxExperimental::class, ExperimentalPermissionsApi::class)
@Composable
fun MapScreen(
    modifier: Modifier=Modifier,
    style: String,
    viewModel: MapScreenViewModel,
    mapViewportState:MapViewportState
) {

    val context = LocalContext.current


    var  currentLoc = rememberUpdatedState(newValue = viewModel.currentUserLocation)






//    AndroidView(factory = {
//
//        MapView(it).also {
//            it.getMapboxMap().loadStyle(style)
//
//            it.annotations.createCircleAnnotationManager().
//        }
//    })


    MapboxMap(
            modifier = Modifier
                .fillMaxSize(),
            mapViewportState =  mapViewportState,
            mapInitOptionsFactory = {context: Context ->

                MapInitOptions(
                        context = context,
                        styleUri = style
                )
            },
            attributionSettings = AttributionSettings {
                style(style){
                }
            },
            onMapClickListener = {
              viewModel.onEvent(
                      MapScreenEvent.OnMapClicked(it){

                      }
              )

                mapViewportState.flyTo(
                        cameraOptions = cameraOptions {
                            this.center(it)
//                                     zoom(1.0)

                        }
                )
                MapAnimationOptions.mapAnimationOptions { duration(5000) }

                false
            }

    ){


        MapEffect(key1 = viewModel.state.mapStyle) {mapview->

            mapview.mapboxMap.loadStyle(viewModel.state.mapStyle)

        }

        viewModel.state.currentPointer?.let {

            MapAnimationOptions.mapAnimationOptions { duration(5000) }
            PointAnnotation(
                    point = it,
                    iconImageBitmap = BitmapFactory.decodeResource(context.resources,R.drawable.pointer),
                    iconSize = 0.06
            )



        }


        viewModel.currentUserLocation?.let {

            Log.d("Pointer", "${it.latitude()}")
            CircleAnnotation(
                    point = it,
                    circleRadius = 6.0,
                    circleStrokeColorInt = android.graphics.Color.WHITE,

                    circleStrokeWidth = 3.0,
                    circleColorInt = android.graphics.Color.BLUE
            )




        }



    }

}

