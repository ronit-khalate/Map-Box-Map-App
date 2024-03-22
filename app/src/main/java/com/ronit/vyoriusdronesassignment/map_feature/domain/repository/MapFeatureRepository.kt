package com.ronit.vyoriusdronesassignment.map_feature.domain.repository

import com.ronit.vyoriusdronesassignment.map_feature.domain.dto.PointerInfoDto

interface MapFeatureRepository {

    suspend fun getPointerInfo(latitude:String,longitude:String):PointerInfoDto
}