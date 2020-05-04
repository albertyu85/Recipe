package com.example.recipe.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "recipe_table")
data class Recipe(
        @PrimaryKey
        val id: Int,
        val image: String = "",
        val readyInMinutes : Int = 0,
        val servings: Int = 0,
        val title: String = "",
        var type: String? = null,
        var detail: String? = null
        )