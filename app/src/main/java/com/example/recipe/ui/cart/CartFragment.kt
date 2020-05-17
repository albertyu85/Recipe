package com.example.recipe.ui.cart

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipe.Injection

import com.example.recipe.R
import com.example.recipe.db.RecipeDatabase
import com.example.recipe.databinding.CartFragmentBinding
import com.example.recipe.model.Cart
import kotlinx.android.synthetic.main.activity_main.*

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
        activity?.toolbar?.title = "Shopping List"
        viewModel = ViewModelProviders.of(this, Injection.provideCartViewModelFactory(context!!)).get(CartViewModel::class.java)
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
