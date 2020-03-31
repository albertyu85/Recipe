package com.example.recipe.ui.directions

import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipe.R
import com.example.recipe.model.Ingredients
import com.example.recipe.model.RecipeInformation
import com.example.recipe.ui.cuisines.CuisinesAdapter
import com.example.recipe.ui.detail.DetailAdapter
import kotlinx.android.synthetic.main.list_item_directions.view.*

class DirectionsAdapter(val listener: () -> Unit) : RecyclerView.Adapter<DirectionsAdapter.ViewHolder>() {

    var data = listOf<Ingredients>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("Directions Adapter", "Binding")
        val item = data[position]
        holder.name.text = item.name
        holder.amount.text = "$${item.amount}"
        holder.bind(listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("Directions Adapter", "Creating")
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_directions, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = data.size


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.item_name
        val amount = itemView.item_amount
        fun bind(clickListener: () -> Unit) {
            itemView.add_button.setOnClickListener { clickListener() }
        }
    }
}

