package com.example.mynewapplication.kotlinpackage.features.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mynewapplication.kotlinpackage.features.data.room.dao.RecipeInfoDao
import com.example.mynewapplication.kotlinpackage.features.data.room.entity.RecipeInfo

@Database(entities = [RecipeInfo::class], version = 1, exportSchema = false)
abstract class RecipesDatabase : RoomDatabase() {

    abstract fun recipeInfoDao(): RecipeInfoDao
}
