package com.example.recipe.data

import android.icu.util.Calendar
import android.provider.Settings
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.recipe.model.Recipe
import com.example.recipe.model.RecipeList
import com.example.recipe.ui.detail.DetailViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.ZonedDateTime
import java.util.*

class RecipeRepository(
    private val type: String,
    private val recipe: String,
    private val recipeDao : RecipeDao,
    private val recipeService : RecipeApi
) {

    init {
        GlobalScope.launch(Dispatchers.IO) {
            if (recipeDao.isEmpty() == 0) {
                Log.d("Repository", "Database empty")
                getAllRecipes()
            }
        }
    }

    suspend fun getAllRecipes() {
        Log.d("Repository", "API")
        val recipeList = when (type) {
            "Cuisine" -> recipeService.retrofitService.getCuisines(recipe).results
            "Diet" -> recipeService.retrofitService.getDiets(recipe).results
            else -> recipeService.retrofitService.getMealTypes(recipe).results
        }
        persistData(recipeList)

    }


    fun fetchRecipes(): LiveData<List<Recipe>> {
        Log.d("Repository", "Database Fetch")
        return recipeDao.getAllRecipes()
    }

    private fun persistData(recipeList: List<Recipe>) {
        GlobalScope.launch(Dispatchers.IO) {
            Log.d("Repository", "Inserting in database")
            for (recipe in recipeList) {
                recipeDao.insert(recipe)
            }
        }
    }

//    suspend fun persistCuisine(recipe: String) {
//
//        val recipeList = recipeService.retrofitService.getCuisines(recipe)
//        for (r in recipeList.results) {
//            recipeDao.insert(r)
//        }
//    }
//
//    suspend fun persistMealType(recipe: String) {
//        val recipeList = recipeService.retrofitService.getMealTypes(recipe)
//        for (r in recipeList.results) {
//            recipeDao.insert(r)
//        }
//    }
//
//    suspend fun persistDietType(recipe: String) {
//        val recipeList = recipeService.retrofitService.getDiets(recipe)
//        for (r in recipeList.results) {
//            recipeDao.insert(r)
//        }
//    }

    private fun isFetchNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinutesAgo)
    }
}