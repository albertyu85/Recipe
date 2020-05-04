package com.example.recipe.ui.cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recipe.data.CartRepository
import com.example.recipe.data.RecipeDatabase
import com.example.recipe.model.Cart

class CartViewModel(recipeDatabase: RecipeDatabase) : ViewModel() {

    val repo = CartRepository(recipeDatabase.cartDao())
    val cart = MutableLiveData<List<Cart>>()

    init {
        repo.fetchCart().observeForever {
            cart.postValue(it)
        }
    }

    fun deleteItem(cart : Cart) {
        repo.deleteItem(cart)
    }
}
