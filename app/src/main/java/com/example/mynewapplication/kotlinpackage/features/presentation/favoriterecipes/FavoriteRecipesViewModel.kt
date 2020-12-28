package com.example.mynewapplication.kotlinpackage.features.presentation.favoriterecipes

import androidx.lifecycle.MutableLiveData
import com.example.mynewapplication.kotlinpackage.core.interactor.UseCase
import com.example.mynewapplication.kotlinpackage.core.platform.BaseViewModel
import com.example.mynewapplication.kotlinpackage.features.domain.model.Recipe
import com.example.mynewapplication.kotlinpackage.features.domain.usecase.DeleteRecipeUseCase
import com.example.mynewapplication.kotlinpackage.features.domain.usecase.GetFavoriteRecipesUseCase
import com.example.mynewapplication.kotlinpackage.features.domain.usecase.SaveRecipeUseCase
import com.example.mynewapplication.kotlinpackage.features.presentation.model.RecipeView
import javax.inject.Inject

class FavoriteRecipesViewModel @Inject constructor(
        private val getFavoriteRecipesUseCase: GetFavoriteRecipesUseCase,
        private val saveRecipeUseCase: SaveRecipeUseCase,
        private val deleteRecipeUseCase: DeleteRecipeUseCase
) : BaseViewModel() {

    var favoriteRecipes: MutableLiveData<List<RecipeView>> = MutableLiveData()

    fun getFavoriteRecipes() = getFavoriteRecipesUseCase(UseCase.None()) {
        it.fold(::handleFailure, ::handleFavoriteRecipes)
    }

    fun saveRecipe(recipeView: RecipeView) = saveRecipeUseCase(SaveRecipeUseCase.Params(recipeView)) {
        it.fold(::handleFailure, ::handleRecipeSaved)
    }

    fun deleteRecipe(href: String) = deleteRecipeUseCase(DeleteRecipeUseCase.Params(href)) {
        it.fold(::handleFailure, ::handleRecipeDeleted)
    }

    private fun handleFavoriteRecipes(recipes: List<Recipe>) {
        val recipesView = recipes.map { it.toRecipeView() }
        recipesView.map { it.isFavorite = true }
        favoriteRecipes.value = recipesView
    }

    private fun handleRecipeSaved(saved: Boolean) {
        // not needed atm
    }

    private fun handleRecipeDeleted(saved: Boolean) {
        // not needed atm
    }
}
