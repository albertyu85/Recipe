package com.example.recipe.ui.detail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController

import com.example.recipe.R
import com.example.recipe.data.RecipeApi
import com.example.recipe.data.RecipeDatabase
import com.example.recipe.data.RecipeRepository
import com.example.recipe.databinding.DetailFragmentBinding
import kotlinx.android.synthetic.main.detail_fragment.*

class DetailFragment : Fragment() {

    companion object {
        fun newInstance() = DetailFragment()
    }

    private lateinit var viewModel: DetailViewModel
    private lateinit var binding : DetailFragmentBinding
    private lateinit var database: RecipeDatabase
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args = DetailFragmentArgs.fromBundle(arguments!!)
        Log.d("DetailFragment", "type: ${args.type} detail: ${args.detail}")
        database = RecipeDatabase.getInstance(this.requireContext())
        binding = DataBindingUtil.inflate(inflater, R.layout.detail_fragment, container, false)
        viewModel = ViewModelProviders.of(this, DetailViewModelFactory(args.type, args.detail, database)).get(DetailViewModel::class.java)
        val adapter = DetailAdapter{clickListener()}

        //Log.d("Response: fragment", viewModel.response?.value?.results?.size.toString())
        viewModel.response.observe(this, Observer {
            adapter.data = it
            detail_list.adapter = adapter
        })
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    private fun clickListener() {
        Toast.makeText(this.context, "On Clicked", Toast.LENGTH_SHORT).show()
    }
}
