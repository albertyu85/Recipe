package com.example.recipe.ui.directions

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide

import com.example.recipe.R
import com.example.recipe.data.RecipeDatabase
import com.example.recipe.databinding.DirectionsFragmentBinding
import com.example.recipe.model.Cart
import com.example.recipe.model.Ingredients
import com.example.recipe.model.RecipeInformation
import kotlinx.android.synthetic.main.directions_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import com.example.recipe.data.CartDao as CartDao

class DirectionsFragment : Fragment() {
    private lateinit var viewModel: DirectionsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val args = DirectionsFragmentArgs.fromBundle(arguments!!)
        val binding: DirectionsFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.directions_fragment, container, false)
        val view = binding.root
        viewModel = ViewModelProviders.of(
            this,
            DirectionsViewModelFactory(args.recipeID)
        ).get(DirectionsViewModel::class.java)
        viewModel.getRecipeInformation()
        val adapter = DirectionsAdapter { cart: Cart -> onClick(cart)}
        binding.recyclerViewIngredientsList.layoutManager = LinearLayoutManager(context)
        binding.progressBar.visibility = View.VISIBLE
        binding.recipeImage.visibility = View.GONE
        viewModel.recipeInfo.observe(this, Observer {
            Glide.with(view)
                .load(it.image)
                .into(binding.recipeImage)
            binding.recipeImage.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
            adapter.data = it.extendedIngredients
            Log.d("Directions Fragment", "${it.extendedIngredients}")
            binding.recyclerViewIngredientsList.adapter = adapter
        })


        val buttonDirections = view.findViewById<Button>(R.id.button_directions)
        buttonDirections.setOnClickListener {
            openDirections(viewModel.recipeInfo.value)
        }
        return view
    }

    fun openDirections(recipeInformation: RecipeInformation?) {
        val openURL = Intent(android.content.Intent.ACTION_VIEW)
        openURL.data = Uri.parse(recipeInformation?.sourceUrl)
        startActivity(openURL)
    }

    private fun onClick(cart: Cart) {
        Toast.makeText(this.context, "Added to Cart", Toast.LENGTH_SHORT).show()
        GlobalScope.launch(Dispatchers.IO) {
            RecipeDatabase.getInstance(context!!).cartDao().insertCart(cart)
        }

    }
}
