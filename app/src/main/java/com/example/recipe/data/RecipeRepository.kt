package com.example.recipe.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.recipe.model.Cart
import com.example.recipe.model.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.ZonedDateTime

class RecipeRepository(
    private val type: String,
    private val detail: String,
    private val recipeDao : RecipeDao,
    private val recipeService : RecipeApi
) {

    init {
        GlobalScope.launch(Dispatchers.IO) {
            if (recipeDao.isTypeAndDetailEmpty(type, detail) == 0) {
                Log.d("Repository", "Type and Detail empty")
                when(type) {
                    "Cuisine" -> persistCuisine()
                    "Diet" -> persistDiet()
                    else -> persistMealType()
                }
            }
        }
    }

    suspend fun refresh() {
        when(type) {
            "Cuisine" -> persistCuisine()
            "Diet" -> persistDiet()
            else -> persistMealType()
        }
    }

    suspend fun getAllRecipes() {
        Log.d("Repository", "API")
        val recipeList = when (type) {
            "Cuisine" -> recipeService.retrofitService.getCuisines(detail).results
            "Diet" -> recipeService.retrofitService.getDiets(detail).results
            else -> recipeService.retrofitService.getMealTypes(detail).results
        }
        persistData(recipeList)

    }

    suspend fun persistCuisine() {
        persistData(recipeService.retrofitService.getCuisines(detail).results)
    }

    suspend fun persistDiet() {
        persistData(recipeService.retrofitService.getDiets(detail).results)
    }

    suspend fun persistMealType() {
        persistData(recipeService.retrofitService.getMealTypes(detail).results)
    }

//    suspend fun persistCart(cart : Cart) {
//        GlobalScope.launch(Dispatchers.IO) {
//            cartDao.insertCart(cart)
//        }
//    }

    fun fetchRecipes(): LiveData<MutableList<Recipe>> {
        Log.d("Repository", "Database Fetch")
        return recipeDao.getTypeAndDetail(type, detail)
    }

    private fun persistData(recipeList: List<Recipe>) {
        GlobalScope.launch(Dispatchers.IO) {
            Log.d("Repository", "Inserting in database")
            for (recipe in recipeList) {
                recipe.type = type
                recipe.detail = detail
                recipeDao.insert(recipe)
            }
        }
    }

    private fun isFetchNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        val twoMinutesAgo = ZonedDateTime.now().minusMinutes(2)
        return lastFetchTime.isBefore(twoMinutesAgo)
    }
}