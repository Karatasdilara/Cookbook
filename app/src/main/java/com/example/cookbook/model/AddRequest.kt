package com.example.cookbook.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AddRequest(
    @SerializedName
        ("name") var name:String
    ,@SerializedName
        ("description")var content: String
) : Serializable {
}
