package com.example.recipe.ui.directions

import androidx.recyclerview.widget.DiffUtil
import com.example.recipe.model.Ingredients

class IngredientsDiffCallback : DiffUtil.ItemCallback<Ingredients>() {
    override fun areItemsTheSame(oldItem: Ingredients, newItem: Ingredients): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Ingredients, newItem: Ingredients): Boolean {
        return oldItem == newItem
    }
}