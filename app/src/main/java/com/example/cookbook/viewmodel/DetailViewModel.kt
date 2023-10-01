package com.example.cookbook.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookbook.model.DetailResponse
import com.example.cookbook.model.Recipe
import com.example.cookbook.repository.RecipeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailViewModel  @Inject constructor (var rrepo : RecipeRepo): ViewModel() {

    var recipeDetail = MutableLiveData<DetailResponse?>()
    var recipeToast = MutableLiveData<String?>()

    suspend fun recipeDetail(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = rrepo.recipeDetail(id)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    recipeDetail.value = response.body()
                } else {
                }
            }
        }
    }
    suspend fun recipeUpdate(recipeId: Int, recipeName: String, recipe: String){
        val recipe = Recipe(recipeId,recipeName,recipe)
        viewModelScope.launch(Dispatchers.IO) {
            val response = rrepo.recipeUpdate(recipe)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    recipeDetail(recipe.id)
                } else {
                    recipeToast.value = response.body()?.message
                }
            }
        }
    }
}