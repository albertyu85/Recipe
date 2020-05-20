package com.example.recipe.ui.directions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recipe.data.CartRepository
import com.example.recipe.api.RecipeApi
import com.example.recipe.data.RecipeRepository
import com.example.recipe.db.RecipeDatabase
import com.example.recipe.model.Cart
import com.example.recipe.model.RecipeInformation
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class DirectionsViewModel(val recipeId: Int, private val cartRepository: CartRepository, private val recipeRepository: RecipeRepository) : ViewModel() {
    private val job = Job()
    private val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)

    private val recipeInfo = MutableLiveData<RecipeInformation>()
    val response : LiveData<RecipeInformation>
        get() = recipeInfo

    fun getRecipeInformation() {
        scope.launch {
            val recipe = recipeRepository.getRecipeByID(recipeId)
            recipeInfo.postValue(recipe)
        }
    }

    fun insertCart(cart: Cart) {
        cartRepository.insertCart(cart)
    }
}
