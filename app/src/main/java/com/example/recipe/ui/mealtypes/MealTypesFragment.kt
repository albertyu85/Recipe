package com.example.recipe.ui.mealtypes

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.recipe.R
import kotlinx.android.synthetic.main.meal_types_fragment.*


class MealTypesFragment : Fragment() {

    companion object {
        fun newInstance() = MealTypesFragment()
    }

    private lateinit var viewModel: MealTypesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.meal_types_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(MealTypesViewModel::class.java)
        val adapter = MealTypesAdapter()
        val layout = GridLayoutManager(this.context, 2)
        adapter.data = viewModel.data
        meal_types_list.adapter = adapter
        meal_types_list.layoutManager = layout
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}
