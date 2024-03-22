package com.ronit.vyoriusdronesassignment.map_feature.presentation.event

import android.location.Location
import com.mapbox.geojson.Point

sealed interface MapScreenEvent {

    data class UpdateCurrentLocation(val location: Location): MapScreenEvent

    data object OnChangeMapTypeIconClicked: MapScreenEvent

    data object OnDismissingMapTypeBottomSheet: MapScreenEvent

    data class OnMapStyleChange(val style:String): MapScreenEvent

    data class OnMapClicked(val point: Point,val moveMap:()->Unit): MapScreenEvent

    data object OnPointerBottomSheetDismissed:MapScreenEvent

    data object AskPermission:MapScreenEvent

}