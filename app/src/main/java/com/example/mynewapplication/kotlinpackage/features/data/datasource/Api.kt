package com.example.mynewapplication.kotlinpackage.features.data.datasource

import com.example.mynewapplication.kotlinpackage.features.data.model.RecipesEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface Api {

    @GET("api/")
    fun recipes(@QueryMap(encoded=true) filters: Map<String, String>): Call<RecipesEntity>
}