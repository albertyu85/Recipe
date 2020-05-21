package com.example.recipe.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.recipe.api.RecipeApi
import com.example.recipe.db.RecipeDatabase
import com.example.recipe.data.RecipeRepository
import com.example.recipe.model.ComplexRecipe
import com.example.recipe.model.ComplexRecipeListResult

class DetailViewModel(private val recipeRepository: RecipeRepository) : ViewModel() {

    val complexRecipeResult = recipeRepository.getComplexRecipe()

//    fun refresh() {
//        scope.launch {
//            repo.refresh()
//        }
//    }


}
