package com.example.recipe.ui.directions

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recipe.data.CartRepository
import com.example.recipe.data.RecipeApi
import com.example.recipe.data.RecipeDatabase
import com.example.recipe.model.Cart
import com.example.recipe.model.RecipeInformation
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class DirectionsViewModel(val recipeId: Int, database: RecipeDatabase) : ViewModel() {
    private val job = Job()
    private val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)

    val repo = CartRepository(database.cartDao())
    init {

    }
    val recipeInfo = MutableLiveData<RecipeInformation>()
    fun getRecipeInformation() {
        scope.launch {
            val recipe = RecipeApi.retrofitService.getRecipe(recipeId)
            recipeInfo.postValue(recipe)
        }
    }

    fun insertCart(cart: Cart) {
        repo.insertCart(cart)
    }
}
