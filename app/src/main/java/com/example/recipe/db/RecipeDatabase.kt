package com.example.recipe.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.recipe.model.Cart
import com.example.recipe.model.ComplexRecipe
import com.example.recipe.model.Recipe

@Database(entities = [Recipe::class, Cart::class, ComplexRecipe::class], version = 7)
abstract class RecipeDatabase : RoomDatabase() {

    abstract fun recipeDao() : RecipeDao
    abstract fun cartDao() : CartDao
    abstract fun complexRecipeDao() : ComplexRecipeDao

    companion object {
        @Volatile private var INSTANCE : RecipeDatabase? = null

        fun getInstance(context: Context) : RecipeDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                        ?: buildDatabase(context).also { INSTANCE = it}
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                RecipeDatabase::class.java,
                "Recipe.db")
                .fallbackToDestructiveMigration()
                .build()
    }
}