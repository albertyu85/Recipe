package com.example.recipe.ui.cart

import androidx.lifecycle.ViewModel
import com.example.recipe.model.Ingredients

class CartViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    val response = listOf(
        Ingredients(1, "test", 3.0, "test", "eggplant", "Metric"),
        Ingredients(2, "test", 3.0, "test", "beef", "Metric"),
        Ingredients(3, "test", 3.0, "test", "lamb", "Metric"),
        Ingredients(4, "test", 3.0, "test", "apple", "Metric"))

}
