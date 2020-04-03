package com.example.recipe.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.recipe.model.Cart
import com.example.recipe.model.Ingredients

@Dao
interface CartDao {

    @Insert
    fun insertCart(cart: Cart)

}