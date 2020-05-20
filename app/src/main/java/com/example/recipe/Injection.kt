package com.example.recipe

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.recipe.api.RecipeApi
import com.example.recipe.api.RecipeApiService
import com.example.recipe.data.CartRepository
import com.example.recipe.data.RecipeRepository
import com.example.recipe.db.CartLocalCache
import com.example.recipe.db.RecipeDatabase
import com.example.recipe.db.RecipeLocalCache
import com.example.recipe.ui.cart.CartViewModel
import com.example.recipe.ui.cart.CartViewModelFactory
import com.example.recipe.ui.detail.DetailViewModelFactory
import com.example.recipe.ui.directions.DirectionsViewModelFactory

object Injection {

    private fun provideRecipeCache(context: Context) : RecipeLocalCache {
        val database = RecipeDatabase.getInstance(context)
        return RecipeLocalCache(database.complexRecipeDao(), database.recipeDao())
    }

    private fun provideCartCache(context: Context) : CartLocalCache {
        val database = RecipeDatabase.getInstance(context)
        return CartLocalCache(database.cartDao())
    }

    private fun provideRecipeRepository(context: Context, id: Int, cuisine: String, diet: String, mealType: String, sort: String) : RecipeRepository {
        val repo = RecipeRepository(provideRecipeCache(context), RecipeApi)
        repo.apply {
            this.id = id
            this.cuisine = cuisine
            this.diet = diet
            this.mealType = mealType
            this.sort = sort
        }
        return repo
    }

    private fun provideCartRepository(context: Context) : CartRepository {
        return CartRepository(provideCartCache(context))
    }

    fun provideCartViewModelFactory(context: Context) : ViewModelProvider.Factory {
        return CartViewModelFactory(provideCartRepository(context))
    }
    fun provideDetailViewModelFactory(context: Context, id: Int, cuisine: String, diet: String, mealType: String, sort: String) : ViewModelProvider.Factory {
        return DetailViewModelFactory(provideRecipeRepository(context, 0, cuisine, diet, mealType, sort))
    }

    fun provideDirectionsViewModelFactory(context: Context, id: Int) : ViewModelProvider.Factory {
        return DirectionsViewModelFactory(id, provideCartRepository(context), provideRecipeRepository(context, id, "", "", "", ""))
    }


}