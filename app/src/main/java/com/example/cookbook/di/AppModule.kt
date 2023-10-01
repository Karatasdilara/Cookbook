package com.example.cookbook.di

import com.example.cookbook.repository.RecipeRepo
import com.example.cookbook.retrofit.ApiUtils
import com.example.cookbook.retrofit.RecipeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideRecipeRepository(rdao: RecipeDao) : RecipeRepo{
        return RecipeRepo(rdao)
    }

    @Provides
    @Singleton
    fun provideRecipeDao() : RecipeDao{
        return ApiUtils.getRecipeDao()
    }
}