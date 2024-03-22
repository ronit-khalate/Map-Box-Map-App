package com.ronit.vyoriusdronesassignment.map_feature.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ronit.vyoriusdronesassignment.map_feature.presentation.MapScreenViewModel
import com.ronit.vyoriusdronesassignment.map_feature.presentation.event.MapScreenEvent


@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun PointerBottomSheet(

    sheetState: SheetState,
    viewmodel:MapScreenViewModel,onDismiss:()->Unit
) {


    

    ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = {

        onDismiss()
    }) {


        Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp)
                        .height(50.dp),

                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
            ) {

                Text(
                        text = viewmodel.state.currentPointerDetails?.place?: "Dropped Pin",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(115.dp)
                        .padding(start = 15.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceEvenly
            ) {


                Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                ) {


                    Text(

                            text = "Address : ",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                    )
                    Spacer(modifier = Modifier.width(15.dp))

                    Text(

                            text = viewmodel.state.currentPointerDetails?.fullAddress ?: "Not Found",
                            fontWeight = FontWeight.Medium,
                            fontSize = 15.sp,
                            maxLines = 1
                    )
                }

                Row(
                        modifier = Modifier
                            .width(200.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                ) {


                    Text(

                            text = "Street : ",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                    )
                    Spacer(modifier = Modifier.width(15.dp))

                    Text(

                            text = viewmodel.state.currentPointerDetails?.street ?: "Not Found",
                            fontWeight = FontWeight.Medium,
                            fontSize = 15.sp,
                            maxLines = 1
                    )
                }




                Row(
                        modifier = Modifier
                            .width(200.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                ) {


                    Text(

                            text = "PostelCode : ",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                    )
                    Spacer(modifier = Modifier.width(15.dp))

                    Text(

                            text = viewmodel.state.currentPointerDetails?.postalCode ?: "Not Found",
                            fontWeight = FontWeight.Medium,
                            fontSize = 15.sp,
                            maxLines = 1
                    )
                }

                Row(
                        modifier = Modifier
                            .width(200.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                ) {


                    Text(

                            text = "Region: ",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,

                    )

                    Spacer(modifier = Modifier.width(15.dp))

                    Text(

                            text = viewmodel.state.currentPointerDetails?.region ?: "Not Found",
                            fontWeight = FontWeight.Medium,
                            fontSize = 15.sp,
                            maxLines = 1
                    )
                }
                Row(
                        modifier = Modifier
                            .width(200.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                ) {


                    Text(

                            text = "District: ",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            maxLines = 1

                    )

                    Spacer(modifier = Modifier.width(15.dp))

                    Text(

                            text = viewmodel.state.currentPointerDetails?.district ?: "NotFound",
                            fontWeight = FontWeight.Medium,
                            fontSize = 15.sp,
                            maxLines = 1
                    )
                }


            }

        }

    }
    
    

}


