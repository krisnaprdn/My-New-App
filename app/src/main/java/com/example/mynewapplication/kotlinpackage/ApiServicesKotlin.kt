package com.example.mynewapplication.kotlinpackage

import com.example.mynewapplication.kotlinpackage.KotlinModel.Result
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiServicesKotlin {
    @GET("api/")
    fun getRecipes(): Call<List<Result>>

    companion object{
        operator fun invoke(): ApiServicesKotlin {
            val requestInterceptor = Interceptor{chain ->
                val url = chain.request()
                        .url()
                        .newBuilder()
                        .build()
                val request = chain.request()
                        .newBuilder()
                        .url(url)
                        .build()
                return@Interceptor chain.proceed(request)
            }
            val okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(requestInterceptor)
                    .build()
            return Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl("http://www.recipepuppy.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiServicesKotlin::class.java)
        }
    }
}