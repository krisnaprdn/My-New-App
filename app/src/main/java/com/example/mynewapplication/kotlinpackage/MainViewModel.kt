package com.example.mynewapplication.kotlinpackage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mynewapplication.kotlinpackage.KotlinModel.Result

class MainViewModel: ViewModel() {
    val results = MutableLiveData<List<Result>>()
}