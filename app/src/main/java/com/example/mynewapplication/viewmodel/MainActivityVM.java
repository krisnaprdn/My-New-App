package com.example.mynewapplication.viewmodel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mynewapplication.model.Results;
import com.example.mynewapplication.retrofit.ApiEndpoint;
import com.example.mynewapplication.retrofit.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityVM extends ViewModel {
    private MutableLiveData<Results> results = new MutableLiveData<>();

    public MutableLiveData<Results> getResults() {
        return results;
    }

    public void fetchRecipe(Context context){
        ApiEndpoint apiEndpoint = ApiService.getApiClient(context).create(ApiEndpoint.class);
        Call<Results> apiCall;
        apiCall = apiEndpoint.getRecipes();
        apiCall.enqueue(new Callback<Results>() {
            @Override
            public void onResponse(Call<Results> call, Response<Results> response) {
                if (response.isSuccessful()){
                    results.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Results> call, Throwable t) {

            }
        });
    }
}
