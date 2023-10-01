package com.example.cookbook.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Recipe(
    @SerializedName
        ("id") var recipe_id:Int,
    @SerializedName
        ("name") var recipe_name:String
    ,@SerializedName
        ("description")var recipe_content: String

) : Serializable {
}