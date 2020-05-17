package com.example.recipe.db

import androidx.lifecycle.LiveData
import com.example.recipe.model.Cart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CartLocalCache(private val cartDao: CartDao) {

    fun getCart() : LiveData<List<Cart>> {
        return cartDao.getCart()
    }

    fun deleteItem(cart: Cart) {
        GlobalScope.launch(Dispatchers.IO) {
            cartDao.deleteItem(cart)
        }
    }

    fun insertCart(cart: Cart) {
        GlobalScope.launch(Dispatchers.IO) {
            cartDao.insertCart(cart)
        }
    }
}