package com.example.recipe.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.recipe.model.Cart
import com.example.recipe.model.ComplexRecipe
import com.example.recipe.model.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.ZonedDateTime

class RecipeRepository(
        private val cuisine: String,
        private val diet: String,
        private val mealType: String,
        private val sort: String,
        private val complexRecipeDao: ComplexRecipeDao,
        private val recipeService: RecipeApi
) {

    init {
        GlobalScope.launch(Dispatchers.IO) {
            if (complexRecipeDao.isComplexEmpty(cuisine, diet, mealType, sort) == 0)
                getComplexRecipe()
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

    suspend fun getComplexRecipe() {
        val list = recipeService.retrofitService.getRecipeComplex(cuisine, diet, mealType, sort)
        persistData(list.results)
    }


    fun fetchComplexRecipe(): LiveData<MutableList<ComplexRecipe>> {
        Log.d("Repository", "Database fetch")
        return complexRecipeDao.getComplex(cuisine, diet, mealType, sort)
    }

    fun persistData(complexRecipeList: MutableList<ComplexRecipe>) {
        Log.d("Repository", "Inserting in database")
        GlobalScope.launch(Dispatchers.IO) {
            Log.d("Repository", "Inserting in database")
            for (recipe in complexRecipeList) {
                recipe.cuisine = cuisine
                recipe.diet = diet
                recipe.mealType = mealType
                recipe.sort = sort
                complexRecipeDao.insert(recipe)
            }
        }
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
}