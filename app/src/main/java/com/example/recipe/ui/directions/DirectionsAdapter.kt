package com.example.recipe.ui.directions

import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipe.R
import com.example.recipe.model.Cart
import com.example.recipe.model.Ingredients
import com.example.recipe.model.RecipeInformation
import com.example.recipe.ui.cuisines.CuisinesAdapter
import com.example.recipe.ui.detail.DetailAdapter
import kotlinx.android.synthetic.main.list_item_directions.view.*

class DirectionsAdapter(val listener: (cart: Cart) -> Unit) : ListAdapter<Ingredients, DirectionsAdapter.ViewHolder>(IngredientsDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("Directions Adapter", "Binding")
        val item = getItem(position)
        holder.name.text = item.original
        holder.bind(listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("Directions Adapter", "Creating")
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_directions, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.item_name
        fun bind(clickListener: (cart: Cart) -> Unit) {
            itemView.add_imageView.setOnClickListener { clickListener(Cart(0, name.text.toString(), "")) }
        }
    }
}

