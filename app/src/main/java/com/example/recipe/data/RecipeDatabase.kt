package com.example.recipe.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.recipe.model.Cart
import com.example.recipe.model.Recipe

@Database(entities = [Recipe::class, Cart::class], version = 5)
abstract class RecipeDatabase : RoomDatabase() {

    abstract fun recipeDao() : RecipeDao
    abstract fun cartDao() : CartDao

    companion object {
        @Volatile private var INSTANCE : RecipeDatabase? = null

        fun getInstance(context: Context) : RecipeDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it}
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                RecipeDatabase::class.java,
                "Recipe.db")
                .fallbackToDestructiveMigration()
                .build()
    }
}