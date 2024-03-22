package com.ronit.vyoriusdronesassignment.map_feature.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.extension.compose.animation.viewport.MapViewportState
import com.ronit.vyoriusdronesassignment.R
import com.ronit.vyoriusdronesassignment.map_feature.presentation.MapScreenViewModel
import com.ronit.vyoriusdronesassignment.map_feature.presentation.event.MapScreenEvent


@OptIn(MapboxExperimental::class, ExperimentalMaterial3Api::class)
@Composable
fun MapOverLay(
    viewModel: MapScreenViewModel,
    pointerBottomSheetState:SheetState,
    moveToCurrentLocation:()->Unit
) {



    Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 7.dp, end = 20.dp)
    ){

        AnimatedVisibility(
                modifier = Modifier
                    .align(Alignment.TopEnd),
                visible = !pointerBottomSheetState.isVisible
        ) {


            Card(
                    modifier = Modifier
                        .padding(top = 100.dp)
                        .size(37.dp)
                        .clickable {
                            viewModel.onEvent(MapScreenEvent.OnChangeMapTypeIconClicked)
                        },
                    shape = CircleShape
            ) {

                Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                ) {


                    Image(
                            modifier = Modifier
                                .size(23.dp)
                                .padding(end = 3.dp),
                            painter = painterResource(id = R.drawable.layers_icon),
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
                            contentDescription = "",

                            )

                }
            }
        }





        Card(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 100.dp)
                    .size(47.dp)
                    .clickable {
                        moveToCurrentLocation()
                    }
                ,
                shape = CircleShape
        ) {

            Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Image(
                        modifier = Modifier
                            .size(28.dp)
                            .padding(end = 3.dp),
                        painter = painterResource(id = R.drawable.go_my_location),
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
                        contentDescription = "",

                )

            }
        }

    }

}