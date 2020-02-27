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
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide

import com.example.recipe.R
import com.example.recipe.databinding.DirectionsFragmentBinding
import com.example.recipe.model.RecipeInformation
import kotlinx.android.synthetic.main.directions_fragment.*

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
        val adapter = DirectionsAdapter()
        binding.recyclerViewIngredientsList.layoutManager = LinearLayoutManager(context)
        viewModel.recipeInfo.observe(this, Observer {
            Glide.with(view)
                .load(it.image)
                .into(binding.recipeImage)
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
}
