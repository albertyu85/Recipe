package com.example.recipe.ui.directions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recipe.data.RecipeDatabase

class DirectionsViewModelFactory(private val recipeId: Int, private val database: RecipeDatabase) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DirectionsViewModel(recipeId, database) as T
    }
}