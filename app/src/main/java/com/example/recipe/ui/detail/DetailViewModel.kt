package com.example.recipe.ui.detail

import androidx.lifecycle.ViewModel
import com.example.recipe.api.RecipeApi
import com.example.recipe.db.RecipeDatabase
import com.example.recipe.data.RecipeRepository

class DetailViewModel(val recipeRepository: RecipeRepository) : ViewModel() {

//    private val _response = MutableLiveData<MutableList<ComplexRecipe>>()
//    val response : LiveData<MutableList<ComplexRecipe>>
//        get() = _response
    val response = recipeRepository.getComplexRecipe()

//    fun refresh() {
//        scope.launch {
//            repo.refresh()
//        }
//    }


}
