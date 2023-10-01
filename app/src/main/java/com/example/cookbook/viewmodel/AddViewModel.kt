package com.example.cookbook.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookbook.model.AddUpdateResponse
import com.example.cookbook.model.AddRequest
import com.example.cookbook.repository.RecipeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel

class AddViewModel  @Inject constructor(var rrepo : RecipeRepo): ViewModel() {

    val add = MutableLiveData<AddUpdateResponse>()

    suspend fun addRecipes(name: String, recipe: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = rrepo.addRecipe(AddRequest(name, recipe))
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    add.value = response.body()
                } else {

                }
            }
        }
    }
}