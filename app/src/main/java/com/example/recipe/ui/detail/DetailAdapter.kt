package com.example.recipe.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipe.R
import com.example.recipe.model.ComplexRecipe

class DetailAdapter(val listener: (recipeID: Int) -> Unit) : PagedListAdapter<ComplexRecipe, DetailAdapter.ViewHolder>(ComplexRecipeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_detail, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
//        holder.progressBar.visibility = View.VISIBLE
        if (item != null) {
            holder.image.visibility = View.INVISIBLE
            holder.title.text = item.title
            holder.id = item.id
            Glide.with(holder.image.context)
                .load("https://spoonacular.com/recipeImages/${item.id}-312x150.jpg")
                .into(holder.image)
//        holder.progressBar.visibility = View.INVISIBLE
            holder.image.visibility = View.VISIBLE
            holder.bind(listener)
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var id = 0
        val title = itemView.findViewById<TextView>(R.id.list_detail_title)
        val image = itemView.findViewById<ImageView>(R.id.imageView)
//        val progressBar = itemView.findViewById<ProgressBar>(R.id.progressBar2)

        fun bind(listener: (recipeID: Int) -> Unit) {
            itemView.setOnClickListener {
                listener(id)
            }
        }
    }
}