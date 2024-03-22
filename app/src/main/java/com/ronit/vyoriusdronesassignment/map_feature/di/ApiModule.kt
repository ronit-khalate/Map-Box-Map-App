package com.ronit.vyoriusdronesassignment.map_feature.di

import com.ronit.vyoriusdronesassignment.map_feature.data.api.ApiService
import com.ronit.vyoriusdronesassignment.map_feature.data.api.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Singleton


@Module
@InstallIn(ViewModelComponent::class)
object ApiModule {



    @Provides
    fun provideApiService():ApiService= RetrofitClient.retrofit.create(ApiService::class.java)
}