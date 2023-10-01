package com.example.cookbook.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookbook.model.Recipe
import com.example.cookbook.model.SearchResponse
import com.example.cookbook.repository.RecipeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(var rrepo : RecipeRepo) : ViewModel() {
    var recipesList = MutableLiveData<List<Recipe>>()
    var recipeSearch = MutableLiveData<SearchResponse>()

    fun getRecipes() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = rrepo.getRecipes()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    recipesList.value = response.body()?.recipes
                } else {
                }
            }
        }
    }

    suspend fun Search(searchWord: String) {
        viewModelScope.launch {
            val response = rrepo.search(searchWord)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    recipeSearch.value = response.body()
                } else {
                }
            }
        }
    }
}