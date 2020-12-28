package com.example.mynewapplication.kotlinpackage.features.domain.usecase

import com.example.mynewapplication.kotlinpackage.core.interactor.UseCase
import com.example.mynewapplication.kotlinpackage.features.data.repository.RecipesRepository
import com.example.mynewapplication.kotlinpackage.features.domain.model.Recipe
import javax.inject.Inject

class GetFavoriteRecipesUseCase @Inject constructor(private val recipesRepository: RecipesRepository) :
    UseCase<List<Recipe>, UseCase.None>() {

    override suspend fun run(params: None) = recipesRepository.favoriteRecipes()
}