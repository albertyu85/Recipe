package com.example.recipe.ui.picker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe.R
import com.example.recipe.ui.cuisines.CuisinesAdapter


class DietAdapter(val listener : (diet: String) -> Unit) : RecyclerView.Adapter<DietAdapter.ViewHolder>() {
    var data = listOf<String>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DietAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.list_item_diet, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: DietAdapter.ViewHolder, position: Int) {
        val item = data[position]
        holder.dietName.text = item
        holder.bind(listener)
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val dietName = itemView.findViewById<TextView>(R.id.list_diet_title)
        fun bind(clickListener : (diet: String) -> Unit) {
            itemView.setOnClickListener {
                clickListener(dietName.text.toString())
            }
        }
    }
}