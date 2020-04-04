package com.example.recipe.data

import androidx.lifecycle.LiveData
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

    @Query("SELECT * FROM cart_table")
    fun getCart() : LiveData<List<Cart>>

}