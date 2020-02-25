package com.example.recipe.model

data class RecipeInformation (
    val id : Int,
    val title: String,
    val image: String,
    val servings: Int,
    val readyInMinutes: Int,
    val sourceUrl: String,
    val extendedIngredients: List<Ingredients>
)
