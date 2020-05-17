package com.example.recipe.ui.detail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.distinctUntilChanged
import androidx.navigation.findNavController
import com.example.recipe.Injection

import com.example.recipe.R
import com.example.recipe.db.RecipeDatabase
import com.example.recipe.databinding.DetailFragmentBinding
import kotlinx.android.synthetic.main.activity_main.*

class DetailFragment : Fragment() {

    companion object {
        fun newInstance() = DetailFragment()
    }

    private lateinit var viewModel: DetailViewModel
    private lateinit var binding : DetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = DataBindingUtil.inflate(inflater, R.layout.detail_fragment, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = DetailFragmentArgs.fromBundle(arguments!!)
        activity?.toolbar?.title = "Recipes"
        Log.d("DetailFragment", "cuisine: ${args.cuisine} diet: ${args.diet}")
        viewModel = ViewModelProviders.of(this,
                Injection.provideDetailViewModelFactory(context!!,
                args.cuisine,
                args.diet,
                args.mealType,
                args.sort))
                .get(DetailViewModel::class.java)

        val adapter = DetailAdapter{recipeID: Int -> clickListener(recipeID)}
        val progressVisibility = viewModel.response.distinctUntilChanged()
        viewModel.response.observe(this, Observer {
            Log.d("Detail", "List Updated")
            adapter.data = it
            binding.detailList.adapter = adapter
        })

//        binding.swipeContainer.setOnRefreshListener {
//            viewModel.refresh()
//            binding.swipeContainer.isRefreshing = false
//        }


    }

    private fun clickListener(recipeID: Int) {
        view?.findNavController()?.navigate(DetailFragmentDirections.actionDetailToDirectionsFragment(recipeID))

    }
}
