package com.example.recipe.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recipe.data.RecipeDatabase

class DetailViewModelFactory(private val type: String, private val detail: String, private val recipeDatabase: RecipeDatabase) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailViewModel(type, detail, recipeDatabase) as T
    }
}