package com.example.mynewapplication.kotlinpackage.features.domain.usecase

import com.example.mynewapplication.kotlinpackage.core.interactor.UseCase
import com.example.mynewapplication.kotlinpackage.features.data.repository.RecipesRepository
import com.example.mynewapplication.kotlinpackage.features.domain.model.Recipe
import com.example.mynewapplication.kotlinpackage.features.presentation.model.RecipeView
import javax.inject.Inject

class SaveRecipeUseCase @Inject constructor(private val recipesRepository: RecipesRepository) :
        UseCase<Boolean, SaveRecipeUseCase.Params>() {

    override suspend fun run(params: Params) =
            recipesRepository.saveRecipe(Recipe(params.recipe.href, params.recipe.ingredients, params.recipe.thumbnail, params.recipe.title))

    data class Params(val recipe: RecipeView)
}