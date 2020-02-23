package com.example.recipe.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.recipe.model.Recipe

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(recipe: Recipe)

    @Query("SELECT * FROM recipe_table")
    fun getAllRecipes() : LiveData<List<Recipe>>

    @Query("SELECT * FROM recipe_table WHERE id = :recipeId")
    fun getRecipe(recipeId: Int) : Recipe

    @Query("SELECT COUNT(*) FROM recipe_table")
    fun isEmpty() : Int

    @Query("SELECT COUNT(*) FROM recipe_table WHERE type = :type AND detail = :detail")
    fun isTypeAndDetailEmpty(type: String, detail: String) : Int

    @Query("SELECT * FROM recipe_table WHERE type = :type AND detail = :detail")
    fun getTypeAndDetail(type: String, detail: String) : LiveData<List<Recipe>>


}