package com.example.mynewapplication.ViewModel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mynewapplication.Model.Response;
import com.example.mynewapplication.Retrofit.ApiEndpoint;
import com.example.mynewapplication.Retrofit.ApiService;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivityVM extends ViewModel {
    private MutableLiveData<Response> results = new MutableLiveData<>();

    public MutableLiveData<Response> getResults() {
        return results;
    }

    public void fetchRecipe(Context context){
        ApiEndpoint apiEndpoint = ApiService.getApiClient(context).create(ApiEndpoint.class);
        Call<Response> apiCall;
        apiCall = apiEndpoint.searchRecipes("search", 1);
        apiCall.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()){
                    results.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });
    }
}
