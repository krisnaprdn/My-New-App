package com.example.mynewapplication.kotlinpackage.features.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RecipeView(
    val href: String,
    val ingredients: String,
    val thumbnail: String,
    val title: String,
    var isFavorite: Boolean = false
) : Parcelable