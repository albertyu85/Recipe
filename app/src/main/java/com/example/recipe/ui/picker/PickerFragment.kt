package com.example.recipe.ui.picker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.recipe.R
import kotlinx.android.synthetic.main.picker_fragment.*

class PickerFragment : Fragment() {

    companion object {
        fun newInstance() = PickerFragment()
    }

    private lateinit var viewModel: PickerViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.picker_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(PickerViewModel::class.java)

        cuisinesList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val cuisinesAdapter =  CuisinesAdapter{ name: String -> onClick(name) }
        cuisinesAdapter.data = viewModel.cuisinesData
        cuisinesList.adapter = cuisinesAdapter

        dietList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val dietAdapter = DietAdapter{ name: String -> onClick(name)}
        dietAdapter.data = viewModel.dietsData
        dietList.adapter = dietAdapter

        meal_types_list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val mealTypesAdapter = MealTypesAdapter{ name: String -> onClick(name)}
        mealTypesAdapter.data = viewModel.mealTypeData
        meal_types_list.adapter = mealTypesAdapter

        filter_list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val filterAdapter = FilterAdapter{ name: String -> onClick(name) }
        filterAdapter.data = viewModel.filterData
        filter_list.adapter = filterAdapter
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    private fun onClick(name: String) {
        Toast.makeText(context, "$name Selected", Toast.LENGTH_SHORT).show()
    }

}
