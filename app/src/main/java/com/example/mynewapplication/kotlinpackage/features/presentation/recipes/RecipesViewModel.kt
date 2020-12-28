package com.example.mynewapplication.kotlinpackage.features.presentation.recipes

import androidx.lifecycle.MutableLiveData
import com.example.mynewapplication.kotlinpackage.core.interactor.UseCase
import com.example.mynewapplication.kotlinpackage.core.platform.BaseViewModel
import com.example.mynewapplication.kotlinpackage.features.domain.model.Recipe
import com.example.mynewapplication.kotlinpackage.features.domain.model.RecipesResult
import com.example.mynewapplication.kotlinpackage.features.domain.usecase.DeleteRecipeUseCase
import com.example.mynewapplication.kotlinpackage.features.domain.usecase.GetFavoriteRecipesUseCase
import com.example.mynewapplication.kotlinpackage.features.domain.usecase.GetRecipesUseCase
import com.example.mynewapplication.kotlinpackage.features.domain.usecase.SaveRecipeUseCase
import com.example.mynewapplication.kotlinpackage.features.presentation.model.RecipeView
import javax.inject.Inject

class RecipesViewModel @Inject constructor(
        private val getRecipesUseCase: GetRecipesUseCase,
        private val getFavoriteRecipesUseCase: GetFavoriteRecipesUseCase,
        private val saveRecipeUseCase: SaveRecipeUseCase,
        private val deleteRecipeUseCase: DeleteRecipeUseCase
) : BaseViewModel() {

    var recipeList: MutableLiveData<List<RecipeView>> = MutableLiveData()
    var favoriteRecipes: MutableLiveData<List<RecipeView>> = MutableLiveData()
    var restartSearch: MutableLiveData<Boolean> = MutableLiveData()

    private var currentPage: Int = 1
    private var currentIngredients: String = ""

    fun getRecipes(ingredients: String) {
        if (currentIngredients != ingredients) {
            currentIngredients = ingredients
            currentPage = 1
            restartSearch.value = true
        }
        getRecipesUseCase(GetRecipesUseCase.Params(currentIngredients, currentPage)) {
            it.fold(::handleFailure, ::handleRecipes)
        }
    }

    fun getRecipesNextPage() = getRecipesUseCase(GetRecipesUseCase.Params(currentIngredients, currentPage)) {
        it.fold(::handleFailure, ::handleRecipes)
    }

    fun getFavoriteRecipes() = getFavoriteRecipesUseCase(UseCase.None()) {
        it.fold(::handleFailure, ::handleFavoriteRecipes)
    }

    fun saveRecipe(recipeView: RecipeView) = saveRecipeUseCase(SaveRecipeUseCase.Params(recipeView)) {
        it.fold(::handleFailure) {}
    }

    fun deleteRecipe(href: String) = deleteRecipeUseCase(DeleteRecipeUseCase.Params(href)) {
        it.fold(::handleFailure) {}
    }

    private fun handleRecipes(recipesResult: RecipesResult) {
        val recipesView = recipesResult.recipes.map { it.toRecipeView() }
        val favoriteRecipesView = recipesResult.favRecipes.map { it.toRecipeView() }

        for (recipeView: RecipeView in recipesView) {
            recipeView.isFavorite = favoriteRecipesView.find { recipeView.href == it.href } != null
        }

        recipeList.value = recipesView
        currentPage++
    }

    private fun handleFavoriteRecipes(recipes: List<Recipe>) {
        val recipesView = recipes.map { it.toRecipeView() }
        recipesView.map { it.isFavorite = true }
        favoriteRecipes.value = recipesView
    }
}
