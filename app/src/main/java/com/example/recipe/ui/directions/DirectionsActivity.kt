package com.example.recipe.ui.directions

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.recipe.R
import com.example.recipe.data.RecipeApi
import com.example.recipe.data.RecipeDatabase
import com.example.recipe.model.Cart
import com.example.recipe.model.RecipeInformation
import com.example.recipe.ui.detail.DetailFragmentArgs
import kotlinx.android.synthetic.main.activity_directions.*
import kotlinx.android.synthetic.main.list_item_detail.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DirectionsActivity : AppCompatActivity() {

    val recipeInfo = MutableLiveData<RecipeInformation>()
    private val database = RecipeDatabase.getInstance(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_directions)
        val args = intent.extras.getInt("recipeID")
        Log.d("Args", args.toString())
        getRecipeInfo(args)
        val adapter = DirectionsAdapter { cart: Cart -> onClick(cart)}

//        recyclerView_ingredients_list.layoutManager = LinearLayoutManager(this)
//        progressBar.visibility = View.VISIBLE
//        expandedImage.visibility = View.GONE

        recipeInfo.observe(this, Observer {
            Glide.with(this)
                    .load(it.image)
                    .into(expandedImage)
//            recipeImage.visibility = View.VISIBLE
//            progressBar.visibility = View.GONE
//            adapter.data = it.extendedIngredients
//            recyclerView_ingredients_list.adapter = adapter
        })

    }

    private fun getRecipeInfo(id: Int) {
        GlobalScope.launch {
            val recipe = RecipeApi.retrofitService.getRecipe(id)
            recipeInfo.postValue(recipe)
        }
    }

    private fun insertCart(cart: Cart) {
        database.cartDao().insertCart(cart)
    }

    private fun onClick(cart: Cart) {
        Toast.makeText(this, "Added to Cart", Toast.LENGTH_SHORT).show()
        insertCart(cart)
    }
}
