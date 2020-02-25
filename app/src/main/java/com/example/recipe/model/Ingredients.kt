package com.example.recipe.model

data class Ingredients(
    val id : Int,
    val aisle: String,
    val amount: Double,
    val image: String,
    val name: String,
    val unit : String
)