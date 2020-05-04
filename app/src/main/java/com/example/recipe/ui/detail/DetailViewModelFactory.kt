package com.example.recipe.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recipe.data.RecipeDatabase

class DetailViewModelFactory(private val cuisine: String,
                             private val diet: String,
                             private val mealType: String,
                             private val sort: String,
                             private val recipeDatabase: RecipeDatabase) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailViewModel(cuisine, diet, mealType, sort, recipeDatabase) as T
    }
}