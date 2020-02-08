package com.example.recipe.ui.detail

import android.util.Log
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

class DetailViewModel(val type: String, val recipe : String) : ViewModel() {
//    private val _response = MutableLiveData<String>()
//    val response: LiveData<String>
//        get() = _response
    val response = liveData {
        val recipeList = when(type) {
            "Cuisine" -> getCuisines(recipe)
            "Diet" -> getDiets(recipe)
            else -> getMealTypes(recipe)
        }


        emit(recipeList)
    }

    private suspend fun getCuisines(cuisine: String) = RecipeApi.retrofitService.getCuisines(cuisine)

    private suspend fun getDiets(diet: String) = RecipeApi.retrofitService.getDiets(diet)

    private suspend fun getMealTypes(mealType : String) = RecipeApi.retrofitService.getMealTypes(mealType)
}
