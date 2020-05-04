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
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class DetailViewModel(val cuisine: String, val diet : String, val mealType : String, val sort : String, recipeDatabase: RecipeDatabase) : ViewModel() {
    private val _response = MutableLiveData<MutableList<Recipe>>()
    private val repo = RecipeRepository(cuisine, diet, mealType, sort, recipeDatabase.recipeDao(), RecipeApi)
    private val job = Job()
    private val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)
    val response: LiveData<MutableList<Recipe>>
        get() = _response


    init {
        scope.launch {
            _response.postValue(RecipeApi.retrofitService.getRecipeComplex(cuisine, diet, mealType, sort).results.toMutableList())
        }
    }

//    fun refresh() {
//        scope.launch {
//            repo.refresh()
//        }
//    }

}
