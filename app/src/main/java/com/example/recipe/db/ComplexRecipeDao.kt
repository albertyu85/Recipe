package com.example.recipe.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.recipe.model.ComplexRecipe
import com.example.recipe.model.Recipe

@Dao
interface ComplexRecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(complexRecipe: ComplexRecipe)

    @Query("SELECT * FROM complex_recipe_table WHERE id = :recipeId")
    fun getComplexRecipe(recipeId: Int) : ComplexRecipe

    @Query("SELECT COUNT(*) FROM complex_recipe_table")
    fun isEmpty() : Int

    @Query("SELECT COUNT(*) FROM complex_recipe_table WHERE id = :id")
    fun containsComplexRecipe(id: Int) : Int

    @Query("SELECT COUNT(*) FROM complex_recipe_table WHERE cuisine = :cuisine AND diet = :diet AND mealType = :mealType AND sort = :sort")
    fun isComplexEmpty(cuisine: String, diet: String, mealType: String, sort: String) : Int

    @Query("SELECT * FROM complex_recipe_table WHERE cuisine = :cuisine AND diet = :diet AND mealType = :mealType AND sort = :sort")
    fun getComplex(cuisine: String, diet: String, mealType: String, sort: String) : LiveData<MutableList<ComplexRecipe>>
}