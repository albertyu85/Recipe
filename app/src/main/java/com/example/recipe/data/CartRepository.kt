package com.example.recipe.data

import androidx.lifecycle.LiveData
import com.example.recipe.db.CartDao
import com.example.recipe.db.CartLocalCache
import com.example.recipe.model.Cart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CartRepository(private val cartLocalCache: CartLocalCache) {

    fun getCart() : LiveData<List<Cart>>{
        return cartLocalCache.getCart()
    }

    fun deleteItem(cart: Cart) {
        GlobalScope.launch(Dispatchers.IO) {
            cartLocalCache.deleteItem(cart)
        }
    }

    fun insertCart(cart: Cart) {
        GlobalScope.launch(Dispatchers.IO) {
            cartLocalCache.insertCart(cart)
        }
    }
}