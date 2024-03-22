package com.ronit.vyoriusdronesassignment.map_feature.data.point_info

data class PointerInfoResponse(
    val attribution: String?,
    val features: List<Feature>,
    val type: String?
)