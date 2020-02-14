package com.example.recipe.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.recipe.model.Recipe

@Dao
interface RecipeDao {

    @Insert
    fun insert(recipe: Recipe)

    @Query("SELECT * FROM recipe_table")
    fun getAllRecipes() : LiveData<List<Recipe>>

    @Query("SELECT * FROM recipe_table WHERE id = :recipeId")
    fun getRecipe(recipeId: Int) : Recipe
}