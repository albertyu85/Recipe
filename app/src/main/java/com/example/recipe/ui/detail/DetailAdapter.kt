package com.example.recipe.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipe.R
import com.example.recipe.model.Recipe
import com.example.recipe.model.RecipeList

class DetailAdapter(val listener : (recipeID: Int) -> Unit) : RecyclerView.Adapter<DetailAdapter.ViewHolder>() {

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
        holder.servings.text = "Servings: ${item.servings}"
        holder.minutes.text = "Ready in: ${item.readyInMinutes} minutes"
        holder.id = item.id
        Glide.with(holder.image.context)
            .load("https://spoonacular.com/recipeImages/${item.id}-312x150.jpg")
            .into(holder.image)

        holder.bind(listener)
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var id = 0
        val title = itemView.findViewById<TextView>(R.id.list_detail_title)
        val servings = itemView.findViewById<TextView>(R.id.servings_textView)
        val minutes = itemView.findViewById<TextView>(R.id.minutes_textView)
        val image = itemView.findViewById<ImageView>(R.id.imageView)
        fun bind(listener: (recipeID: Int) -> Unit) {
            itemView.setOnClickListener {
                listener(id)
            }
        }
    }
}