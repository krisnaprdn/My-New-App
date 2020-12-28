package com.example.mynewapplication.kotlinpackage.features.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mynewapplication.kotlinpackage.features.data.room.entity.RecipeInfo

@Dao
interface RecipeInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(recipeInfo: RecipeInfo): Long

    @Query("DELETE FROM recipes WHERE href = :href")
    fun delete(href: String): Int

    @get:Query("SELECT * FROM recipes")
    val favoriteRecipes: List<RecipeInfo>
}