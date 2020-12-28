package com.example.mynewapplication.kotlinpackage.features.domain.model

import com.example.mynewapplication.kotlinpackage.features.data.room.entity.RecipeInfo
import com.example.mynewapplication.kotlinpackage.features.presentation.model.RecipeView

data class Recipe(
    private val href: String,
    private val ingredients: String,
    private val thumbnail: String,
    private val title: String
) {
    fun toRecipeView() = RecipeView(href, ingredients, thumbnail, title)

    fun toRecipeInfo() = RecipeInfo(href, ingredients, thumbnail, title)

    companion object {
        fun empty() = Recipe("", "", "", "")
    }
}
