package com.example.recipe.data

import android.util.Log
import androidx.lifecycle.LiveData
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
//
//        GlobalScope.launch(Dispatchers.IO) {
//            if (recipeDao.isTypeAndDetailEmpty(type, detail) == 0) {
//                Log.d("Repository", "Type and Detail empty")
//                getAllRecipes()
//            }
//        }
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


    fun fetchRecipes(): LiveData<List<Recipe>> {
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

//    suspend fun persistCuisine(detail: String) {
//
//        val recipeList = recipeService.retrofitService.getCuisines(detail)
//        for (r in recipeList.results) {
//            recipeDao.insert(r)
//        }
//    }
//
//    suspend fun persistMealType(detail: String) {
//        val recipeList = recipeService.retrofitService.getMealTypes(detail)
//        for (r in recipeList.results) {
//            recipeDao.insert(r)
//        }
//    }
//
//    suspend fun persistDietType(detail: String) {
//        val recipeList = recipeService.retrofitService.getDiets(detail)
//        for (r in recipeList.results) {
//            recipeDao.insert(r)
//        }
//    }

    private fun isFetchNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinutesAgo)
    }
}