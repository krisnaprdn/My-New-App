package com.example.mynewapplication.ViewModel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MainActivityVMF implements ViewModelProvider.Factory {
    private final Context context;

    public MainActivityVMF(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return(T) new MainActivityVM();
    }
}
