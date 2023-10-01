package com.example.cookbook.retrofit

class ApiUtils {
    companion object {
        val BASE_URL =  "https://api.canerture.com/recipes/"

        fun getRecipeDao(): RecipeDao {
            return RetrofitClient.getClient(BASE_URL).create(RecipeDao::class.java)
        }
    }
}