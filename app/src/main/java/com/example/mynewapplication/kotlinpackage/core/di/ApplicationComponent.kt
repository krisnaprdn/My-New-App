package com.example.mynewapplication.kotlinpackage.core.di

import com.example.mynewapplication.kotlinpackage.KotlinActivity.ContentListActivity
import com.example.mynewapplication.kotlinpackage.RecipePuppyApp
import com.example.mynewapplication.kotlinpackage.core.di.viewmodel.ViewModelModule
import com.example.mynewapplication.kotlinpackage.features.presentation.MainActivityKotlin
import com.example.mynewapplication.kotlinpackage.features.presentation.favoriterecipes.FavoriteRecipesFragment
import com.example.mynewapplication.kotlinpackage.features.presentation.recipedetails.RecipeDetailsFragment
import com.example.mynewapplication.kotlinpackage.features.presentation.recipes.RecipesFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class, DataModule::class, ViewModelModule::class, DatabaseModule::class])
interface ApplicationComponent {

    fun inject(application: RecipePuppyApp)

    fun inject(mainActivityKotlin: MainActivityKotlin)

    fun inject(recipesFragment: RecipesFragment)

    fun inject(recipeDetailsFragment: RecipeDetailsFragment)

    fun inject(favoriteRecipesFragment: FavoriteRecipesFragment)
}
