package com.example.recipe.model

data class Recipe(val id: Int,
             val image: String = "",
             val readyInMinutes : Int,
             val servings: Int,
             val title: String)