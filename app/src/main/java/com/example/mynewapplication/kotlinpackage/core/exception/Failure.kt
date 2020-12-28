package com.example.mynewapplication.kotlinpackage.core.exception

import java.lang.Exception

sealed class Failure {

    abstract class BaseFailure : Failure()

    class NetworkConnection : BaseFailure()
    class ServerError(val throwable: Throwable?) : BaseFailure()
    class BodyNullError : BaseFailure()
    class GetRecipesError : BaseFailure()
    class DbGetFavoriteRecipesError(val exception: Exception) : BaseFailure()
    class DbInsertError : BaseFailure()
    class DbDeleteError : BaseFailure()
}