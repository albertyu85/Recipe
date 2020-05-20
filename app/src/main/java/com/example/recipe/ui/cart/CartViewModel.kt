package com.example.recipe.ui.cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recipe.data.CartRepository
import com.example.recipe.db.RecipeDatabase
import com.example.recipe.model.Cart

class CartViewModel(val cartRepository: CartRepository) : ViewModel() {

    val cart = MutableLiveData<List<Cart>>()

    init {
        cartRepository.getCart().observeForever {
            cart.postValue(it)
        }
    }

    fun deleteItem(cart : Cart) {
        cartRepository.deleteItem(cart)
    }
}
