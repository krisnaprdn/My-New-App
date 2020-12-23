package com.example.mynewapplication.Utils

import com.example.mynewapplication.Retrofit.ApiServicesKotlin
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
        private const val BASE_URL = "http://www.recipepuppy.com/"

    val INSTANCE: ApiServicesKotlin by lazy {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        retrofit.create(ApiServicesKotlin::class.java)
    }
}