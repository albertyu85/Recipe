package com.example.recipe

data class Recipe(val id : Int,
                  val title: String,
                  val image: String,
                  val servings: Int,
                  val readyInMinutes: Int,
                  val sourceName : String,
                  val spoonacularSourceUrl: String)