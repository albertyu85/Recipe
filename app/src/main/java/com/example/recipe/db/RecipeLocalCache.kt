package com.example.recipe.db

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.recipe.model.ComplexRecipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RecipeLocalCache(private val complexRecipeDao: ComplexRecipeDao,
                       private val recipeDao: RecipeDao) {

    fun isComplexEmpty(cuisine: String,
                       diet: String,
                       mealType: String,
                       sort: String) = complexRecipeDao.isComplexEmpty(cuisine, diet, mealType, sort)

    fun insertComplexRecipeList(complexRecipeList: MutableList<ComplexRecipe>,
                                cuisine: String,
                                diet: String,
                                mealType: String,
                                sort: String) {

        GlobalScope.launch(Dispatchers.IO) {
            for (complexRecipe in complexRecipeList) {
                complexRecipe.apply {
                    this.cuisine = cuisine
                    this.diet = diet
                    this.mealType = mealType
                    this.sort = sort
                }
                complexRecipeDao.insert(complexRecipe)
            }
        }
    }

    fun getComplexRecipe(cuisine: String,
                         diet: String,
                         mealType: String,
                         sort: String) : DataSource.Factory<Int, ComplexRecipe> {
        return complexRecipeDao.getComplex(cuisine, diet, mealType, sort)
    }
}