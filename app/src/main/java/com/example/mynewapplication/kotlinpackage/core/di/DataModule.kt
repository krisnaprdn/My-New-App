package com.example.mynewapplication.kotlinpackage.core.di

import com.example.mynewapplication.kotlinpackage.features.data.repository.RecipesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideRecipesRepository(dataSource: RecipesRepository.Network): RecipesRepository = dataSource
}
