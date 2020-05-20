package com.example.recipe.ui.directions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recipe.data.CartRepository
import com.example.recipe.data.RecipeRepository
import com.example.recipe.db.RecipeDatabase

class DirectionsViewModelFactory(private val recipeId: Int, private val cartRepository: CartRepository, private val recipeRepository: RecipeRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DirectionsViewModel(recipeId, cartRepository, recipeRepository) as T
    }
}