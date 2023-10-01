package com.example.cookbook.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RecipesResponse(
    @SerializedName("recipes")
    var recipes: List<Recipe>,
    @SerializedName("status")
    var status: Int,
    @SerializedName("message")
    var message: String
) : Serializable