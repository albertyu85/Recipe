package com.example.recipe.model

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

class ComplexRecipeListResult(val data: LiveData<PagedList<ComplexRecipe>>) {

}