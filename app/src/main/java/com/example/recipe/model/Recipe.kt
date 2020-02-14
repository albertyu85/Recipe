package com.example.recipe.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe_table")
data class Recipe(
        @PrimaryKey
        val id: Int,
        val image: String = "",
        val readyInMinutes : Int,
        val servings: Int,
        val title: String
        )