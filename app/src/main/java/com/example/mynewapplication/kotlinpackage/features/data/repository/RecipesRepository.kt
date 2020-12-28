package com.example.mynewapplication.kotlinpackage.features.data.repository

import arrow.core.Either
import com.example.mynewapplication.kotlinpackage.core.exception.Failure
import com.example.mynewapplication.kotlinpackage.core.platform.NetworkHandler
import com.example.mynewapplication.kotlinpackage.features.data.datasource.RecipesService
import com.example.mynewapplication.kotlinpackage.features.data.room.dao.RecipeInfoDao
import com.example.mynewapplication.kotlinpackage.features.data.room.entity.RecipeInfo
import com.example.mynewapplication.kotlinpackage.features.domain.model.Recipe
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import javax.inject.Inject

interface RecipesRepository {

    fun recipes(ingredients: String, page: Int): Either<Failure, List<Recipe>>

    fun favoriteRecipes(): Either<Failure, List<Recipe>>

    fun saveRecipe(recipe: Recipe): Either<Failure, Boolean>

    fun deleteRecipe(href: String): Either<Failure, Boolean>

    class Network @Inject constructor(
            private val networkHandler: NetworkHandler,
            private val service: RecipesService,
            private val recipeInfoDao: RecipeInfoDao
    ) : RecipesRepository {

        override fun recipes(ingredients: String, page: Int): Either<Failure, List<Recipe>> {
            return when (networkHandler.isConnected) {
                true -> request(
                    service.recipes(
                        mapOf(
                            "i" to ingredients,
                            //"q" to "omelet",
                            "p" to page.toString()
                        )
                    )
                ) { recipesEntity -> recipesEntity.results.map { it.toRecipe() } }
                false, null -> Either.Left(Failure.NetworkConnection())
            }
        }

        override fun favoriteRecipes(): Either<Failure, List<Recipe>> {
            return runBlocking {
                var result: List<RecipeInfo>? = null
                launch { result = recipeInfoDao.favoriteRecipes }.join()
                result?.let {
                    Either.Right(it.map { recipeInfo -> recipeInfo.toRecipe() })
                } ?: Either.Left(Failure.DbGetFavoriteRecipesError(Exception("Couldn't get the recipes saved")))
            }
        }

        override fun saveRecipe(recipe: Recipe): Either<Failure, Boolean> {
            return runBlocking {
                var result: Long? = null
                launch { result = recipeInfoDao.insert(recipe.toRecipeInfo()) }.join()
                result?.let {
                    Either.Right(true)
                } ?: Either.Left(Failure.DbInsertError())
            }
        }

        override fun deleteRecipe(href: String): Either<Failure, Boolean> {
            return runBlocking {
                var result: Int? = null
                launch { result = recipeInfoDao.delete(href) }.join()
                result?.let {
                    Either.Right(true)
                } ?: Either.Left(Failure.DbDeleteError())
            }
        }

        private fun <T, R> request(call: Call<T>, transform: (T) -> R): Either<Failure, R> {
            return try {
                val response = call.execute()
                when (response.isSuccessful) {
                    true -> {
                        response.body()?.let { Either.Right(transform(it)) }
                            ?: Either.Left(Failure.BodyNullError())
                    }
                    false -> Either.Left(Failure.ServerError(Throwable(response.errorBody()?.string())))
                }
            } catch (exception: Throwable) {
                Either.Left(Failure.ServerError(exception))
            }
        }
    }
}