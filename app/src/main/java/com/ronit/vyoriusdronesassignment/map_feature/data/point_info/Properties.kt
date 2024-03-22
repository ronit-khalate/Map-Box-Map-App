package com.ronit.vyoriusdronesassignment.map_feature.data.point_info

data class Properties(
    val bbox: List<Double>?,
    val context: Context?,
    val coordinates: Coordinates?,
    val feature_type: String?,
    val full_address: String?,
    val mapbox_id: String?,
    val name: String?,
    val place_formatted: String?
)