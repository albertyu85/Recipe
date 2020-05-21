package com.example.recipe.api

import com.example.recipe.model.ComplexRecipe

data class ComplexRecipeListResponse(val offset: Int, val number : Int, val results: MutableList<ComplexRecipe>) {
}