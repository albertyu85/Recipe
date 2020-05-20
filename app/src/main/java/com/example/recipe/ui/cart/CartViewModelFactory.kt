package com.example.recipe.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recipe.data.CartRepository
import com.example.recipe.db.RecipeDatabase

class CartViewModelFactory(private val cartRepository: CartRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CartViewModel(cartRepository) as T
    }
}