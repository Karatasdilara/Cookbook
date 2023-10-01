package com.example.cookbook.retrofit

import com.example.cookbook.model.AddUpdateResponse
import com.example.cookbook.model.DetailResponse
import com.example.cookbook.model.Recipe
import com.example.cookbook.model.AddRequest
import com.example.cookbook.model.SearchResponse
import com.example.cookbook.model.RecipesResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface RecipeDao {

    @GET("get_recipes.php")
    suspend fun recipes(): Response<RecipesResponse>

    @GET("search_recipe.php")
    suspend fun search(@Query("query") query: String): Response<SearchResponse>

    @GET("get_recipe_detail.php")
    suspend fun recipeDetail(@Query("id") id: Int): Response<DetailResponse>

    @POST("add_recipe.php")
    suspend fun addRecipe(@Body request: AddRequest): Response<AddUpdateResponse>

    @POST("update_recipe.php")
    suspend fun recipeUpdate(@Body request: Recipe): Response<AddUpdateResponse>

}