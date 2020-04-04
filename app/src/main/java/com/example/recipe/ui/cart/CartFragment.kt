package com.example.recipe.ui.cart

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.recipe.R
import com.example.recipe.data.RecipeDatabase
import com.example.recipe.ui.detail.DetailViewModel
import com.example.recipe.ui.detail.DetailViewModelFactory
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
        val database = RecipeDatabase.getInstance(context!!)
        viewModel = ViewModelProviders.of(this, CartViewModelFactory(database)).get(CartViewModel::class.java)
        val cartAdapter = CartAdapter()

        cart_list.adapter = cartAdapter
        viewModel.cart.observe(this, Observer {
            cartAdapter.cart = it
        })
        cart_list.layoutManager = LinearLayoutManager(context)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}
