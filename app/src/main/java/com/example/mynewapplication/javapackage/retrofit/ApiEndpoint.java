package com.example.mynewapplication.javapackage.retrofit;

import com.example.mynewapplication.javapackage.model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiEndpoint {
    @GET("api/")
    Call<ResponseModel> getRecipes();
}
