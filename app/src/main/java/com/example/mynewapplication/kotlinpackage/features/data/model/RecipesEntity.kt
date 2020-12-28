package com.example.mynewapplication.kotlinpackage.features.data.model

import com.example.mynewapplication.kotlinpackage.features.domain.model.Recipe

data class RecipesEntity(
    val href: String,
    val results: List<RecipeEntity>,
    val title: String,
    val version: Double
)

data class RecipeEntity(
    val href: String,
    val ingredients: String,
    val thumbnail: String,
    val title: String
) {
    companion object {
        fun empty() = RecipeEntity("", "", "", "")
    }

    fun toRecipe() = Recipe(href, ingredients, thumbnail, title)
}
