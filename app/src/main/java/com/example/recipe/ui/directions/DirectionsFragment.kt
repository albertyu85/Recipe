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
import com.example.recipe.Injection

import com.example.recipe.R
import com.example.recipe.db.RecipeDatabase
import com.example.recipe.databinding.DirectionsFragmentBinding
import com.example.recipe.model.Cart
import com.example.recipe.model.RecipeInformation
import kotlinx.android.synthetic.main.activity_main.*

class DirectionsFragment : Fragment() {
    private lateinit var viewModel: DirectionsViewModel
    private lateinit var binding: DirectionsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.toolbar?.title = "Directions"
        val args = DirectionsFragmentArgs.fromBundle(arguments!!)
        binding = DataBindingUtil.inflate(inflater, R.layout.directions_fragment, container, false)
        val view = binding.root
        viewModel = ViewModelProviders.of(
            this,
            Injection.provideDirectionsViewModelFactory(context!!, args.recipeID)
        ).get(DirectionsViewModel::class.java)
        viewModel.getRecipeInformation()
        val adapter = DirectionsAdapter { cart: Cart -> onClick(cart)}
        binding.apply {
            invalidateAll()
            recyclerViewIngredientsList.layoutManager = LinearLayoutManager(context)
            progressBar.visibility = View.VISIBLE
            recipeImage.visibility = View.GONE
        }

        viewModel.recipeInfo.observe(this, Observer {
            if (it.image == "") {
                Glide.with(view)
                        .load(R.drawable.beach_scene)
                        .into(binding.recipeImage)
            }
            else {
                Glide.with(view)
                        .load(it.image)
                        .into(binding.recipeImage)
            }

            binding.apply {
                recipeImage.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
            }
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
        viewModel.insertCart(cart)
    }
}
