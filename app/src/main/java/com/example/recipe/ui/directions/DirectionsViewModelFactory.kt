package com.example.recipe.ui.directions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DirectionsViewModelFactory(private val recipeId: Int) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DirectionsViewModel(recipeId) as T
    }
}