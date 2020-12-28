package com.example.mynewapplication.kotlinpackage.core.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mynewapplication.kotlinpackage.features.presentation.favoriterecipes.FavoriteRecipesViewModel
import com.example.mynewapplication.kotlinpackage.features.presentation.recipes.RecipesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(RecipesViewModel::class)
    abstract fun bindsRecipesViewModel(recipesViewModel: RecipesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteRecipesViewModel::class)
    abstract fun bindsFavoriteRecipesViewModel(favoriteRecipesViewModel: FavoriteRecipesViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
