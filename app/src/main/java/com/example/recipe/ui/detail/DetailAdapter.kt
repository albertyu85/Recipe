package com.example.recipe.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe.R
import com.example.recipe.model.Recipe
import com.example.recipe.model.RecipeList

class DetailAdapter(val listener : () -> Unit) : RecyclerView.Adapter<DetailAdapter.ViewHolder>() {

    var data = listOf<Recipe>()
    set(value)  {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_detail, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.title.text = item.title
        holder.bind(listener)
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.list_detail_title)
        fun bind(listener: () -> Unit) {
            itemView.setOnClickListener {
                listener()
            }
        }
    }
}