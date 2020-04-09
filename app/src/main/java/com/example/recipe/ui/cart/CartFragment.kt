package com.example.recipe.ui.cart

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.recipe.R
import com.example.recipe.data.RecipeDatabase
import com.example.recipe.databinding.CartFragmentBinding
import com.example.recipe.model.Cart
import com.example.recipe.ui.detail.DetailViewModel
import com.example.recipe.ui.detail.DetailViewModelFactory
import com.example.recipe.ui.mealtypes.MealTypesAdapter
import kotlinx.android.synthetic.main.cart_fragment.*
import kotlinx.android.synthetic.main.list_item_cart.*
import kotlinx.android.synthetic.main.meal_types_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CartFragment : Fragment() {

    companion object {
        fun newInstance() = CartFragment()
    }

    private lateinit var viewModel: CartViewModel
    private lateinit var binding : CartFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.cart_fragment, container, false)
        val view = binding.root
        val database = RecipeDatabase.getInstance(this.requireContext())
        viewModel = ViewModelProviders.of(this, CartViewModelFactory(database)).get(CartViewModel::class.java)
        val cartAdapter = CartAdapter{ cart : Cart -> onClick(cart)}

        viewModel.cart.observe(this, Observer {
            cartAdapter.cart = it
            binding.apply {
                cartList.adapter = cartAdapter
                cartList.layoutManager = LinearLayoutManager(context)
            }
        })

        return view
    }

    private fun onClick(cart : Cart) {
        viewModel.deleteItem(cart)
    }

}
