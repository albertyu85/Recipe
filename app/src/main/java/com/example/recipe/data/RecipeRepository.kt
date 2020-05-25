package com.example.recipe.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.recipe.api.RecipeApi
import com.example.recipe.db.ComplexRecipeDao
import com.example.recipe.db.RecipeLocalCache
import com.example.recipe.model.ComplexRecipe
import com.example.recipe.model.ComplexRecipeListResult
import com.example.recipe.model.RecipeInformation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.ZonedDateTime

class RecipeRepository(
        private val recipeLocalCache: RecipeLocalCache,
        private val recipeService: RecipeApi
) {
    var id = 0
    var cuisine = ""
    var diet = ""
    var mealType = ""
    var sort = ""

    init {
        GlobalScope.launch(Dispatchers.IO) {
            if (recipeLocalCache.isComplexEmpty(cuisine, diet, mealType, sort) == 0) {
                Log.d("Repository", "Database Empty")
                fetchComplexRecipe()
            }
        }
    }
//    suspend fun refresh() {
//        when(type) {
//            "Cuisine" -> persistCuisine()
//            "Diet" -> persistDiet()
//            else -> persistMealType()
//        }
//    }

//    suspend fun getAllRecipes() {
//        Log.d("Repository", "API")
//        val recipeList = when (type) {
//            "Cuisine" -> recipeService.retrofitService.getCuisines(detail).results
//            "Diet" -> recipeService.retrofitService.getDiets(detail).results
//            else -> recipeService.retrofitService.getMealTypes(detail).results
//        }
//        persistData(recipeList)
//
//    }

    suspend fun getRecipeByID(id: Int) : RecipeInformation {
        return recipeService.retrofitService.getRecipe(id)
    }
    private suspend fun fetchComplexRecipe() {
        Log.d("Repository", "API Fetch")
        val list = recipeService.retrofitService.getRecipeComplex(cuisine, diet, mealType, sort)
        insertComplex(list.results)
    }


    fun getComplexRecipe(): ComplexRecipeListResult {
        Log.d("Repository", "Database fetch")

        val dataSourceFactory = recipeLocalCache.getComplexRecipe(cuisine, diet, mealType, sort)
        val data = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
            .setBoundaryCallback(ComplexBoundaryCallback(recipeService, recipeLocalCache, cuisine, diet, mealType, sort))
            .build()
        return ComplexRecipeListResult(data)
    }

    private fun insertComplex(complexRecipeList: MutableList<ComplexRecipe>) {
        Log.d("Repository", "Inserting in database")
        recipeLocalCache.insertComplexRecipeList(complexRecipeList, cuisine, diet, mealType, sort)
    }
//    suspend fun persistCuisine() {
//        persistData(recipeService.retrofitService.getCuisines(detail).results)
//    }
//
//    suspend fun persistDiet() {
//        persistData(recipeService.retrofitService.getDiets(detail).results)
//    }
//
//    suspend fun persistMealType() {
//        persistData(recipeService.retrofitService.getMealTypes(detail).results)
//    }

//    suspend fun persistCart(cart : Cart) {
//        GlobalScope.launch(Dispatchers.IO) {
//            cartDao.insertCart(cart)
//        }
//    }

//    fun fetchRecipes(): LiveData<MutableList<Recipe>> {
//        Log.d("Repository", "Database Fetch")
//        return recipeDao.getTypeAndDetail(type, detail)
//    }

//    private fun persistData(recipeList: List<Recipe>) {
//        GlobalScope.launch(Dispatchers.IO) {
//            Log.d("Repository", "Inserting in database")
//            for (recipe in recipeList) {
//                recipe.type = type
//                recipe.detail = detail
//                recipeDao.insert(recipe)
//            }
//        }
//    }

    private fun isFetchNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        val twoMinutesAgo = ZonedDateTime.now().minusMinutes(2)
        return lastFetchTime.isBefore(twoMinutesAgo)
    }

    companion object {
        private const val DATABASE_PAGE_SIZE = 20
    }
}