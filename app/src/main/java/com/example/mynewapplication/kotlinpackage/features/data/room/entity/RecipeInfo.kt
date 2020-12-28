package com.example.mynewapplication.kotlinpackage.features.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mynewapplication.kotlinpackage.features.domain.model.Recipe

@Entity(tableName = "recipes")
data class RecipeInfo(
        @PrimaryKey
        @ColumnInfo(name = "href") var href: String,
        @ColumnInfo(name = "ingredients") var ingredients: String,
        @ColumnInfo(name = "thumbnail") var thumbnail: String,
        @ColumnInfo(name = "title") var title: String
) {
    fun toRecipe() = Recipe(href, ingredients, thumbnail, title)

    companion object {
        fun empty() = RecipeInfo("", "", "", "")
    }
}
