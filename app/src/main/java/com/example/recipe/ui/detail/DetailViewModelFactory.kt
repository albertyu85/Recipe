package com.example.recipe.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DetailViewModelFactory(private val type: String, private val detail: String) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailViewModel(type, detail) as T
    }
}