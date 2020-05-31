package com.example.recipe.data

import android.provider.Settings
import android.util.Log
import androidx.paging.PagedList
import com.example.recipe.api.RecipeApi
import com.example.recipe.api.RecipeApiService
import com.example.recipe.db.RecipeLocalCache
import com.example.recipe.model.ComplexRecipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ComplexBoundaryCallback(private val recipeService: RecipeApi,
                              private val cache: RecipeLocalCache,
                              private val cuisine: String,
                              private val diet: String,
                              private val mealType: String,
                              private val sort: String) : PagedList.BoundaryCallback<ComplexRecipe>() {

    private var lastRequestedOffset = 0
    private var isRequestInProgress = false

    override fun onZeroItemsLoaded() {
        requestAndSaveData()
    }

    override fun onItemAtEndLoaded(itemAtEnd: ComplexRecipe) {
        requestAndSaveData()
    }

    private fun requestAndSaveData() {
        Log.d("Repository", "ComplexBoundaryCallback Request")
        Log.d("Repository", "Offset: $lastRequestedOffset")
        if (isRequestInProgress) return

        isRequestInProgress = true
        GlobalScope.launch(Dispatchers.IO) {
            Log.d("Repository", "API Call")
            val list = recipeService.retrofitService.getRecipeComplex(cuisine, diet, mealType, sort, lastRequestedOffset)
            cache.insertComplexRecipeList(list.results, cuisine, diet, mealType, sort)
            isRequestInProgress = false
            lastRequestedOffset += 20
        }

    }
}