package com.ronit.vyoriusdronesassignment.map_feature.data.api

object ApiClient {

    val apiService : ApiService by lazy {

        RetrofitClient.retrofit.create(ApiService::class.java)
    }
}