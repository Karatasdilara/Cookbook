package com.example.cookbook.repository

import com.example.cookbook.model.Recipe
import com.example.cookbook.model.AddRequest
import com.example.cookbook.retrofit.RecipeDao


class RecipeRepo (var rdao : RecipeDao){


    suspend fun getRecipes() = rdao.recipes()

    suspend fun addRecipe(request: AddRequest) = rdao.addRecipe(request)

    suspend fun recipeUpdate(recipe: Recipe) = rdao.recipeUpdate(recipe)

    suspend fun recipeDetail(id:Int) = rdao.recipeDetail(id)

    suspend fun search(searchWord:String) = rdao.search(searchWord)

}