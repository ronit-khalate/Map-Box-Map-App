package com.ronit.vyoriusdronesassignment.map_feature.data.api

import com.ronit.vyoriusdronesassignment.BuildConfig
import com.ronit.vyoriusdronesassignment.map_feature.data.point_info.PointerInfoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("reverse")
    suspend fun getPointerDetails(
        @Query("longitude") long: String,
        @Query("latitude") lat: String,
        @Query("access_token") tkn: String = BuildConfig.MAP_API_KEY
    ): PointerInfoResponse
}