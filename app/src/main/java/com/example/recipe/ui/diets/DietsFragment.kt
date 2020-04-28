package com.example.recipe.ui.diets

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
import com.example.recipe.databinding.DietsFragmentBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.diets_fragment.*


class DietsFragment : Fragment() {

    companion object {
        fun newInstance() = DietsFragment()
    }

    private lateinit var viewModel: DietsViewModel
    private lateinit var binding: DietsFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.toolbar?.title = "Diets"
        binding = DataBindingUtil.inflate(inflater, R.layout.diets_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel = ViewModelProviders.of(this).get(DietsViewModel::class.java)
        val adapter = DietAdapter {diet: String -> onClick(diet)}
        val manager = GridLayoutManager(this.context, 2)
        adapter.data = viewModel.data
        binding.apply {
            dietList.adapter = adapter
            dietList.layoutManager = manager
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun onClick(recipe: String) {
        view?.findNavController()?.navigate(DietsFragmentDirections.actionDietsFragmentToDetail("Diet", recipe ))
    }
}
