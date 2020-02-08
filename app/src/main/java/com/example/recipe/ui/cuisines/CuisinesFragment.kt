package com.example.recipe.ui.cuisines

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe.R
import com.example.recipe.ui.detail.DetailFragment
import kotlinx.android.synthetic.main.cuisines_fragment.*


class CuisinesFragment : Fragment() {

    companion object {
        fun newInstance() = CuisinesFragment()
    }

    private lateinit var viewModel: CuisinesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.cuisines_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(CuisinesViewModel::class.java)
        val adapter = CuisinesAdapter {cuisineTitle : String -> onClick(cuisineTitle)}
        adapter.data = viewModel.data
        val layoutManager = GridLayoutManager(this.context, 2)
        cuisinesList.adapter = adapter
        cuisinesList.layoutManager = layoutManager


        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    private fun onClick(title: String) {
        view?.findNavController()?.navigate(CuisinesFragmentDirections.actionCuisinesFragmentToDetail("Cuisine", title))
    }

}
