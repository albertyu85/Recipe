package com.example.recipe.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.recipe.R
import com.example.recipe.ui.detail.DetailFragmentArgs
import kotlinx.android.synthetic.main.directions_fragment.*

class DirectionsFragment : Fragment() {

    companion object {
        fun newInstance() = DirectionsFragment()
    }

    private lateinit var viewModel: DirectionsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args = DirectionsFragmentArgs.fromBundle(arguments!!)
        val view =inflater.inflate(R.layout.directions_fragment, container, false)
        Log.d("Directions", "Id: ${args.recipeID}")
        val textView = view.findViewById<TextView>(R.id.textView)
        textView.text = args.recipeID.toString()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DirectionsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
