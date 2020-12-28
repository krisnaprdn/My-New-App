package com.example.mynewapplication.kotlinpackage.features.domain.model

data class RecipesResult(
    val recipes: List<Recipe>,
    val favRecipes: List<Recipe>
)