package com.example.recipe.ui.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe.R
import com.example.recipe.model.Ingredients
import com.example.recipe.ui.detail.DetailAdapter
import kotlinx.android.synthetic.main.list_item_cart.view.*

class CartAdapter : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    var ingredients = listOf<Ingredients>()
    set(value) {
        field =  value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_cart, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return ingredients.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = ingredients[position]
        holder.name.text = item.name

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.list_cart_title
    }
}

