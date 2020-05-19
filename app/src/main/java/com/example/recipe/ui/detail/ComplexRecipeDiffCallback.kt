package com.example.recipe.ui.detail

import androidx.recyclerview.widget.DiffUtil
import com.example.recipe.model.ComplexRecipe

class ComplexRecipeDiffCallback : DiffUtil.ItemCallback<ComplexRecipe>() {

    override fun areItemsTheSame(oldItem: ComplexRecipe, newItem: ComplexRecipe): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ComplexRecipe, newItem: ComplexRecipe): Boolean {
        return oldItem == newItem
    }

}