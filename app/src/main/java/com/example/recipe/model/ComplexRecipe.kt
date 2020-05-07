package com.example.recipe.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "complex_recipe_table")
data class ComplexRecipe(
        @PrimaryKey
        var id: Int,
        var calories: Int = 0,
        var carbs: Int = 0,
        var image: String,
        var imageType: String,
        var protein: String = "0g",
        var title: String,
        var cuisine: String = "",
        var mealType: String = "",
        var diet: String = "",
        var sort: String = ""
) {
}