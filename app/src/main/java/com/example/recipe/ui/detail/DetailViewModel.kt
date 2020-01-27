package com.example.recipe.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.recipe.data.RecipeApi
import com.example.recipe.model.Recipe
import com.example.recipe.model.RecipeList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {
//    private val _response = MutableLiveData<String>()
//    val response: LiveData<String>
//        get() = _response
    val response = liveData {
        val recipeList = getCuisines("Chinese")
        emit(recipeList)
    }
    val test = "test"

    private suspend fun getCuisines(cuisine: String) = RecipeApi.retrofitService.getCuisines(cuisine)
}
