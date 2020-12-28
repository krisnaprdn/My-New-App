package com.example.mynewapplication.kotlinpackage.KotlinModel


data class ContentListResponse(
        val href: String,
        val results: List<Result>,
        val title: String,
        val version: Double
)