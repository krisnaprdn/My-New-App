package com.example.mynewapplication.javapackage.viewmodel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mynewapplication.javapackage.model.ResponseModel;
import com.example.mynewapplication.javapackage.model.Results;
import com.example.mynewapplication.javapackage.retrofit.ApiEndpoint;
import com.example.mynewapplication.javapackage.retrofit.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityVM extends ViewModel {
    private MutableLiveData<ResponseModel> results = new MutableLiveData<>();

    public MutableLiveData<ResponseModel> getResults() {
        return results;
    }

    public void fetchRecipe(Context context){
        ApiEndpoint apiEndpoint = ApiService.getApiClient(context).create(ApiEndpoint.class);
        Call<ResponseModel> apiCall;
        apiCall = apiEndpoint.getRecipes();
        apiCall.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.isSuccessful()){
                    results.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {

            }
        });
    }
}
