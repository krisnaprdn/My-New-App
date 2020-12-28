package com.example.mynewapplication.kotlinpackage.core.extension

import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.Factory
import androidx.lifecycle.ViewModelProviders
import com.example.mynewapplication.kotlinpackage.core.platform.BaseFragment
import com.example.mynewapplication.kotlinpackage.features.presentation.MainActivityKotlin
import kotlinx.android.synthetic.main.activity_kotlinmain.*

inline fun <reified T : ViewModel> Fragment.viewModel(factory: Factory, body: T.() -> Unit): T {
    val vm = ViewModelProviders.of(this, factory)[T::class.java]
    vm.body()
    return vm
}

val BaseFragment.viewContainer: View get() = (activity as MainActivityKotlin).root

val BaseFragment.appContext: Context get() = activity?.applicationContext!!
