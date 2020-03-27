package com.example.recipe.ui.cart

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.recipe.R
import com.example.recipe.ui.mealtypes.MealTypesAdapter
import kotlinx.android.synthetic.main.cart_fragment.*
import kotlinx.android.synthetic.main.meal_types_fragment.*

class CartFragment : Fragment() {

    companion object {
        fun newInstance() = CartFragment()
    }

    private lateinit var viewModel: CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       return inflater.inflate(R.layout.cart_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(CartViewModel::class.java)
        val cartAdapter = CartAdapter()

        cart_list.adapter = cartAdapter
        cartAdapter.ingredients = viewModel.response
        cart_list.layoutManager = LinearLayoutManager(context)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


//
//        val adapter = MealTypesAdapter{recipe: String -> onClick(recipe)}
//        val layout = GridLayoutManager(this.context, 2)
//        adapter.data = viewModel.data
//        meal_types_list.adapter = adapter
//        meal_types_list.layoutManager = layout
    }

}
