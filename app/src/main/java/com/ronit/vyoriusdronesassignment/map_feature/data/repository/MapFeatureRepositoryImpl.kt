package com.ronit.vyoriusdronesassignment.map_feature.data.repository

import com.ronit.vyoriusdronesassignment.map_feature.data.api.ApiService
import com.ronit.vyoriusdronesassignment.map_feature.data.toPointerInfoDto
import com.ronit.vyoriusdronesassignment.map_feature.domain.dto.PointerInfoDto
import com.ronit.vyoriusdronesassignment.map_feature.domain.repository.MapFeatureRepository
import javax.inject.Inject

class MapFeatureRepositoryImpl @Inject constructor(
        private val apiService:ApiService
):MapFeatureRepository {

    override suspend fun getPointerInfo(latitude: String, longitude: String):PointerInfoDto {

        return  apiService.getPointerDetails(longitude,latitude).toPointerInfoDto()
    }
}