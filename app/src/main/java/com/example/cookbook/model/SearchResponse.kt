package com.example.cookbook.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SearchResponse(
    @SerializedName("recipes")
    var recipes: List<Recipe>,
    @SerializedName("status")
    var status: Int
) : Serializable
