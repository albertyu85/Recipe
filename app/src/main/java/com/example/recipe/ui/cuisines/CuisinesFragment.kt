package com.example.recipe.ui.cuisines

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe.R
import com.example.recipe.databinding.CuisinesFragmentBinding
import com.example.recipe.ui.detail.DetailFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.cuisines_fragment.*


class CuisinesFragment : Fragment() {

    companion object {
        fun newInstance() = CuisinesFragment()
    }

    private lateinit var viewModel: CuisinesViewModel
    private lateinit var binding: CuisinesFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.toolbar?.title = "Cuisines"
        binding = DataBindingUtil.inflate(inflater, R.layout.cuisines_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(CuisinesViewModel::class.java)
        val adapter = CuisinesAdapter {cuisineTitle : String -> onClick(cuisineTitle)}
        val layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        adapter.data = viewModel.data
        binding.apply {
            cuisinesList.adapter = adapter
            cuisinesList.layoutManager = layoutManager
        }


        super.onViewCreated(view, savedInstanceState)
    }

    private fun onClick(title: String) {
//        view?.findNavController()?.navigate(CuisinesFragmentDirections.actionCuisinesFragmentToDetail("Cuisine", title))
    }

}
