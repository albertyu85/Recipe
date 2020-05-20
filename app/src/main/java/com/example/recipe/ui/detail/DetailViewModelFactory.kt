package com.example.recipe.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recipe.data.RecipeRepository
import com.example.recipe.db.RecipeDatabase

class DetailViewModelFactory(private val recipeRepository: RecipeRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailViewModel(recipeRepository) as T
    }
}