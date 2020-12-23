package com.example.mynewapplication.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mynewapplication.KotlinModel.Result

class MainViewModel: ViewModel() {
    val results = MutableLiveData<List<Result>>()
}