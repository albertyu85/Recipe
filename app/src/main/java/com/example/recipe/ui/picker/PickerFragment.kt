package com.example.recipe.ui.picker

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipe.ProgressButton

import com.example.recipe.R
import kotlinx.android.synthetic.main.picker_fragment.*

class PickerFragment : Fragment() {

    companion object {
        fun newInstance() = PickerFragment()
    }

    private lateinit var viewModel: PickerViewModel

    private var cuisine : String = ""
    private var mealType: String = ""
    private var diet = ""
    private var sort = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.picker_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("Picker", "Cuisine: $cuisine, Meal Type: $mealType, Diet: $diet, Sort: $sort")
        viewModel = ViewModelProviders.of(this).get(PickerViewModel::class.java)

        cuisinesList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val cuisinesAdapter =  CuisinesAdapter{ name: String -> onClick("cuisine", name) }
        cuisinesAdapter.data = viewModel.cuisinesData
        cuisinesList.adapter = cuisinesAdapter

        dietList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val dietAdapter = DietAdapter{ name: String -> onClick("diet", name)}
        dietAdapter.data = viewModel.dietsData
        dietList.adapter = dietAdapter

        meal_types_list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val mealTypesAdapter = MealTypesAdapter{ name: String -> onClick("meal type", name)}
        mealTypesAdapter.data = viewModel.mealTypeData
        meal_types_list.adapter = mealTypesAdapter

        filter_list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val filterAdapter = FilterAdapter{ name: String -> onClick("sort", name) }
        filterAdapter.data = viewModel.filterData
        filter_list.adapter = filterAdapter

//        search_button.setOnClickListener {
//            view.findNavController().navigate(PickerFragmentDirections.actionPickerFragmentToDetail(cuisine, diet, mealType, sort))
//        }

        progress_button.setOnClickListener {
            Log.d("Picker", "Cuisine: $cuisine, Meal Type: $mealType, Diet: $diet, Sort: $sort")
            val progressButton = ProgressButton(context!!, it)
            progressButton.buttonActivated()

            val handler = Handler()
            handler.postDelayed(Runnable {
                progressButton.buttonFinished()
                view.findNavController().navigate(PickerFragmentDirections.actionPickerFragmentToDetail(cuisine, diet, mealType, sort))
            }, 3000)

        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun onClick(query: String, name: String) {
        when (query) {
            "cuisine" -> if (cuisine == name) {
                cuisine = ""
            } else {
                cuisine = name
            }
            "diet" -> if (diet == name) diet = "" else diet = name
            "meal type" -> if (mealType == name) mealType = "" else mealType = name
            "sort" -> if (sort == name) sort = "" else sort = name
        }
        Toast.makeText(context, "$query: $name Selected", Toast.LENGTH_SHORT).show()
        Log.d("Picker", "Cuisine: $cuisine, Meal Type: $mealType, Diet: $diet, Sort: $sort")
//        view?.findNavController()?.navigate(PickerFragmentDir)
    }

}
