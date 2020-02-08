package com.example.recipe.ui.cuisines

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe.MainActivity
import com.example.recipe.R
import com.example.recipe.ui.detail.DetailFragment

class CuisinesAdapter(val listener: (title : String) -> Unit) : RecyclerView.Adapter<CuisinesAdapter.ViewHolder>() {
    var data = listOf<String>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CuisinesAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.list_item_cuisines, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: CuisinesAdapter.ViewHolder, position: Int) {
        val item = data[position]
        holder.cuisineName.text = item
        holder.bind(listener)

    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val cuisineName = itemView.findViewById<TextView>(R.id.list_cuisine_title)
        fun bind(clickListener : (title : String) -> Unit) {
            itemView.setOnClickListener {
                clickListener(cuisineName.text.toString())
            }
        }
    }

}
