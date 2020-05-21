package com.example.recipe.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recipe.api.RecipeApi
import com.example.recipe.db.RecipeDatabase
import com.example.recipe.data.RecipeRepository
import com.example.recipe.model.ComplexRecipe
import com.example.recipe.model.ComplexRecipeListResult

class DetailViewModel(val recipeRepository: RecipeRepository) : ViewModel() {

    private val _response = MutableLiveData<ComplexRecipeListResult>()
    val response : LiveData<ComplexRecipeListResult>
        get() = _response



    fun getResponse() {
        val data = recipeRepository.getComplexRecipe()
        _response.postValue(data)
    }
//    fun refresh() {
//        scope.launch {
//            repo.refresh()
//        }
//    }


}
