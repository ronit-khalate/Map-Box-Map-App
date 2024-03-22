package com.ronit.vyoriusdronesassignment.map_feature.presentation.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mapbox.maps.Style
import com.ronit.vyoriusdronesassignment.R
import com.ronit.vyoriusdronesassignment.map_feature.presentation.MapScreenViewModel
import com.ronit.vyoriusdronesassignment.map_feature.presentation.event.MapScreenEvent


@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun MapTypeBottomSheet(
    viewModel: MapScreenViewModel,
    sheetState:SheetState
) {


    val localConfiguration = LocalConfiguration.current
    val screenHeight = localConfiguration.screenHeightDp


    ModalBottomSheet(
            onDismissRequest = {viewModel.onEvent(MapScreenEvent.OnDismissingMapTypeBottomSheet) },



            ) {


        Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                    modifier= Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly

            ) {

                MapTypeIcon(image = R.drawable.street, name = "Street") {
                    viewModel.onEvent(MapScreenEvent.OnMapStyleChange(Style.MAPBOX_STREETS))
                    Log.d("stylechange","on style change  ${viewModel.state.mapStyle}")
                }

                MapTypeIcon(image = R.drawable.satellite, name = "Satellite") {
                    viewModel.onEvent(MapScreenEvent.OnMapStyleChange(Style.SATELLITE))
                    Log.d("stylechange","on style change clicked  ${viewModel.state.mapStyle}")


                }

                MapTypeIcon(image = R.drawable.hybrid_map_icon, name = "Hybrid") {
                    viewModel.onEvent(MapScreenEvent.OnMapStyleChange(Style.OUTDOORS))
                    Log.d("stylechange","on style change clicked  ${viewModel.state.mapStyle}")

                }

            }

        }

    }
}



@Composable
fun MapTypeIcon(
    image: Int,
    name:String,
    onClick:()->Unit
) {

Column(
        modifier = Modifier
            .width(60.dp)
            .height(110.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
) {




        Image(
                modifier =Modifier
                    .clickable {
                               onClick()
                    },
                painter = painterResource(id = image),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
                contentDescription = "",
                contentScale = ContentScale.Fit
        )






    Spacer(modifier = Modifier.height(0.dp))
    Text(
            text = name,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onBackground // Ensure text color contrasts well with background
    )

}



    
}

@Preview(showBackground = true, name = "MapTypeIcon")
@Composable
private fun MapTypeIconPreview() {
    MapTypeIcon(R.drawable.street,"fadf"){}
}