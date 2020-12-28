package com.example.mynewapplication.kotlinpackage.core.platform

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mynewapplication.kotlinpackage.core.exception.Failure

abstract class BaseViewModel : ViewModel() {

    var failure: MutableLiveData<Failure> = MutableLiveData()

    protected fun handleFailure(failure: Failure?) {
        this.failure.value = failure
    }
}