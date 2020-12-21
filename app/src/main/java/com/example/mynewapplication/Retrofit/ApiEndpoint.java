package com.example.mynewapplication.Retrofit;

import com.example.mynewapplication.Model.MainModel;
import com.example.mynewapplication.Model.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiEndpoint {
    @GET("api/")
    Call<Response> searchRecipes(
            @Query("q") String searchTerm,
            @Query("p") int page
    );
}
