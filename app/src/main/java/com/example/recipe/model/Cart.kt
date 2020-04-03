package com.example.recipe.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_table")
data class Cart(
        @PrimaryKey
        val name : String,
        val amount : String
)
