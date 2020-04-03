package com.example.recipe.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_table")
data class Cart(
        @PrimaryKey
        val id : Int,
        val name : String,
        val aisle : String,
        val amount : Double,
        val unit : String
)
