package com.example.mynewapplication.kotlinpackage.core.platform

import android.content.Context
import com.example.mynewapplication.kotlinpackage.core.extension.networkInfo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkHandler
@Inject constructor(private val context: Context) {

    val isConnected get() = context.networkInfo?.isConnectedOrConnecting
}
