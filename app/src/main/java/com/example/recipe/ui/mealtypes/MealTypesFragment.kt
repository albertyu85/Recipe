package com.example.recipe.ui.mealtypes

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.recipe.R
import com.example.recipe.databinding.MealTypesFragmentBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.meal_types_fragment.*


class MealTypesFragment : Fragment() {

    companion object {
        fun newInstance() = MealTypesFragment()
    }

    private lateinit var viewModel: MealTypesViewModel
    private lateinit var binding: MealTypesFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.toolbar?.title = "Meal Types"
        binding = DataBindingUtil.inflate(inflater, R.layout.meal_types_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(MealTypesViewModel::class.java)
        val adapter = MealTypesAdapter{recipe: String -> onClick(recipe)}
        val layout = GridLayoutManager(this.context, 2)
        adapter.data = viewModel.data
        binding.apply {
            meal_types_list.adapter = adapter
            meal_types_list.layoutManager = layout
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun onClick(recipe: String) {
        //view?.findNavController()?.navigate(MealTypesFragmentDirections.actionMealTypesFragmentToDetail("Meal Type", recipe))
    }

}
