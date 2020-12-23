package com.example.mynewapplication.KotlinModel


import com.google.gson.annotations.SerializedName

data class Result(
    val href: String,
    val ingredients: String,
    val thumbnail: String,
    val title: String
)