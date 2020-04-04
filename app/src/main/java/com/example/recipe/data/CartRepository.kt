package com.example.recipe.data

import androidx.lifecycle.LiveData
import com.example.recipe.model.Cart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CartRepository(private val cartDao: CartDao) {

    fun fetchCart() : LiveData<List<Cart>>{
        return cartDao.getCart()
    }
}