package com.example.cookbook.model

import com.google.gson.annotations.SerializedName

data class DetailResponse (@SerializedName("recipe") var recipe:Recipe,
                           @SerializedName("status") var status:Int,
                           @SerializedName("message")var message:String){
}