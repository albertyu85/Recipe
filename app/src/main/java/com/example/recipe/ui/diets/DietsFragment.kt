package com.example.recipe.ui.diets

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.recipe.R
import kotlinx.android.synthetic.main.diets_fragment.*


class DietsFragment : Fragment() {

    companion object {
        fun newInstance() = DietsFragment()
    }

    private lateinit var viewModel: DietsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.diets_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = DietAdapter()
        val manager = GridLayoutManager(this.context, 2)
        val data = listOf("Gluten Free", "Ketogenic", "Vegetarian", "Lacto-Vegetarian", "Oro-Vegetarian", "Vegan", "Pescetarian",
            "Paleo", "Whole 30")
        adapter.data = data
        dietList.adapter = adapter
        dietList.layoutManager = manager
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DietsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
