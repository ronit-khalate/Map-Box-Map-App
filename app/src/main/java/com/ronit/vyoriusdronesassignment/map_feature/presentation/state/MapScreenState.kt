package com.ronit.vyoriusdronesassignment.map_feature.presentation.state

import com.mapbox.geojson.Point
import com.mapbox.maps.Style
import com.ronit.vyoriusdronesassignment.map_feature.domain.dto.PointerInfoDto

data class MapScreenState(
        val mapTypeBottomSheetVisible:Boolean = false,
        val mapStyle: String = Style.OUTDOORS,
        val currentPointer:Point?=null,
        val currentPointerDetails:PointerInfoDto?=null,
        val showPointerInfoBottomSheet:Boolean=false
)
