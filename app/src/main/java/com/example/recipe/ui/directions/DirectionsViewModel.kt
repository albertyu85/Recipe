package com.example.recipe.ui.directions

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recipe.data.RecipeApi
import com.example.recipe.model.RecipeInformation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DirectionsViewModel(val recipeId: Int) : ViewModel() {
    private val job = Job()
    private val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)

    val recipeInfo = MutableLiveData<RecipeInformation>()

    fun getRecipeInformation() {
        scope.launch {
            val recipe = RecipeApi.retrofitService.getRecipe(recipeId)
            recipeInfo.postValue(recipe)
        }

    }
}
