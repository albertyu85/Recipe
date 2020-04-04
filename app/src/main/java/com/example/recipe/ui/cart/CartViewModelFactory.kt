package com.example.recipe.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recipe.data.RecipeDatabase
import com.example.recipe.ui.directions.DirectionsViewModel

class CartViewModelFactory(private val recipeDatabase: RecipeDatabase) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CartViewModel(recipeDatabase) as T
    }
}