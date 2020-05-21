package com.example.recipe.api

import com.example.recipe.model.ComplexRecipe

data class ComplexRecipeListResponse(val offset: Int = 0, val number: Int = 0, val results: MutableList<ComplexRecipe>) {
}