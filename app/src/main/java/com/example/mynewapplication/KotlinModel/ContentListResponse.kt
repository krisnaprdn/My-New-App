package com.example.mynewapplication.KotlinModel


import com.google.gson.annotations.SerializedName

data class ContentListResponse(
    val href: String,
    val results: List<Result>,
    val title: String,
    val version: Double
)