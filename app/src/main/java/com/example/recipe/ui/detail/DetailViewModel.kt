package com.example.recipe.ui.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.recipe.data.RecipeApi
import com.example.recipe.data.RecipeApiService
import com.example.recipe.data.RecipeDatabase
import com.example.recipe.data.RecipeRepository
import com.example.recipe.model.Recipe
import com.example.recipe.model.RecipeList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(val type: String, val recipe : String, recipeDatabase: RecipeDatabase) : ViewModel() {
    private val _response = MutableLiveData<List<Recipe>>()
    val response: LiveData<List<Recipe>>
        get() = _response

    init {
        val repo = RecipeRepository(type, recipe, recipeDatabase.recipeDao(), RecipeApi)
        repo.fetchRecipes().observeForever {
            Log.d("DetailViewModel", "Observing change")
            _response.postValue(it)
        }
    }

}
