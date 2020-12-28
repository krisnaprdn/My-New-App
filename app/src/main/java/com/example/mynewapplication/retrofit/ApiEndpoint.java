package com.example.mynewapplication.retrofit;

import com.example.mynewapplication.model.Results;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiEndpoint {
    @GET("api/")
    Call<Results> getRecipes();
}
