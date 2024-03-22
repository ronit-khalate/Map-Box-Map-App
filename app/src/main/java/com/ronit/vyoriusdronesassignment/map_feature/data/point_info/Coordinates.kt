package com.ronit.vyoriusdronesassignment.map_feature.data.point_info

data class Coordinates(
    val latitude: Double,
    val longitude: Double,
    val routable_points: List<RoutablePoint>
)