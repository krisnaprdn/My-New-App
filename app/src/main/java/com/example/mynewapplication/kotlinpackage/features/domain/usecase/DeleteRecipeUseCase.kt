package com.example.mynewapplication.kotlinpackage.features.domain.usecase

import arrow.core.Either
import com.example.mynewapplication.kotlinpackage.core.exception.Failure
import com.example.mynewapplication.kotlinpackage.core.interactor.UseCase
import com.example.mynewapplication.kotlinpackage.features.data.repository.RecipesRepository
import javax.inject.Inject

class DeleteRecipeUseCase @Inject constructor(private val recipesRepository: RecipesRepository) :
        UseCase<Boolean, DeleteRecipeUseCase.Params>() {

    override suspend fun run(params: Params) = recipesRepository.deleteRecipe(params.href)

    data class Params(val href: String)
}