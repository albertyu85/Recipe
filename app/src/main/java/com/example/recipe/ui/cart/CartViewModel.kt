package com.example.recipe.ui.cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recipe.data.CartRepository
import com.example.recipe.data.RecipeDatabase
import com.example.recipe.data.RecipeRepository
import com.example.recipe.model.Cart
import com.example.recipe.model.Ingredients

class CartViewModel(recipeDatabase: RecipeDatabase) : ViewModel() {

    val cart = MutableLiveData<List<Cart>>()

    init {
        val repo = CartRepository(recipeDatabase.cartDao())
        repo.fetchCart().observeForever {
            cart.postValue(it)
        }
    }
}
