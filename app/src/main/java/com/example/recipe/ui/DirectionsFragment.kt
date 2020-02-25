package com.example.recipe.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide

import com.example.recipe.R
import com.example.recipe.data.RecipeApi
import com.example.recipe.databinding.DirectionsFragmentBinding
import com.example.recipe.model.Recipe
import com.example.recipe.ui.detail.DetailFragmentArgs
import kotlinx.android.synthetic.main.directions_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DirectionsFragment : Fragment() {
    private lateinit var viewModel: DirectionsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val args = DirectionsFragmentArgs.fromBundle(arguments!!)
        val binding : DirectionsFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.directions_fragment, container, false)
        val view = binding.root
        viewModel = ViewModelProviders.of(this, DirectionsViewModelFactory(args.recipeID)).get(DirectionsViewModel::class.java)
        viewModel.getRecipeInformation()
        viewModel.recipeInfo.observe(this, Observer {
            Glide.with(view)
                .load(it.image)
                .into(binding.recipeImage)
        })

        return view
    }
}
