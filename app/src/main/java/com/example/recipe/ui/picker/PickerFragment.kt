package com.example.recipe.ui.picker

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipe.ui.customviews.ProgressButton

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
        val cuisinesAdapter =  CuisinesAdapter()
        cuisinesAdapter.data = viewModel.cuisinesData
        cuisinesList.adapter = cuisinesAdapter

        dietList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val dietAdapter = DietAdapter()
        dietAdapter.data = viewModel.dietsData
        dietList.adapter = dietAdapter

        meal_types_list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val mealTypesAdapter = MealTypesAdapter()
        mealTypesAdapter.data = viewModel.mealTypeData
        meal_types_list.adapter = mealTypesAdapter

        filter_list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val filterAdapter = FilterAdapter()
        filterAdapter.data = viewModel.filterData
        filter_list.adapter = filterAdapter

//        search_button.setOnClickListener {
//            view.findNavController().navigate(PickerFragmentDirections.actionPickerFragmentToDetail(cuisine, diet, mealType, sort))
//        }

        progress_button.setOnClickListener {
            var cuisine = checkCuisines(cuisinesAdapter.checked)
            var mealType = checkMealTypes((mealTypesAdapter.checked))
            var diet = checkDiet(dietAdapter.checked)
            var sort = checkSort(filterAdapter.checked)
            Log.d("Picker", "Cuisine: $cuisine, Meal Type: $mealType, Diet: $diet, Sort: $sort")
            val progressButton = ProgressButton(context!!, it)
            progressButton.buttonActivated()

            val handler = Handler()
            handler.postDelayed(Runnable {
                progressButton.buttonFinished()
                view.findNavController().navigate(PickerFragmentDirections.actionPickerFragmentToDetail(cuisine, diet, mealType, sort))
            }, 1000)

        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun onClick(query: String, name: String) {
//        Toast.makeText(context, "$query: $name Selected", Toast.LENGTH_SHORT).show()
    }

    private fun checkCuisines(checked: SparseBooleanArray) : String {
        for (i in viewModel.cuisinesData.indices) {
            if (checked.get(i, false))
                return viewModel.cuisinesData[i]
        }
        return ""
    }

    private fun checkDiet(checked: SparseBooleanArray) : String {
        for (i in viewModel.dietsData.indices) {
            if (checked.get(i, false))
                return viewModel.dietsData[i]
        }
        return ""
    }

    private fun checkMealTypes(checked: SparseBooleanArray) : String {
        for (i in viewModel.mealTypeData.indices) {
            if (checked.get(i, false))
                return viewModel.mealTypeData[i]
        }
        return ""
    }

    private fun checkSort(checked: SparseBooleanArray) : String {
        for (i in viewModel.filterData.indices) {
            if (checked.get(i))
                return viewModel.filterData[i]
        }
        return ""
    }

}
