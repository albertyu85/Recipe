package com.example.recipe.ui.picker

import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe.R
import kotlinx.android.synthetic.main.list_item_meal_types.view.*

class MealTypesAdapter : ListAdapter<String, MealTypesAdapter.ViewHolder>(TypeDiffCallback()) {

    val checked = SparseBooleanArray()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context)
        val view = layout.inflate(R.layout.list_item_meal_types, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.title.text = item
        holder.bind(position)
    }

    inner class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        val title = itemView.list_meal_type_title
        val imageOverlay = itemView.findViewById<ImageView>(R.id.image_view_overlay)
        fun bind(position: Int) {
            if (!checked.get(position, false))
                imageOverlay.visibility = View.INVISIBLE
            else
                imageOverlay.visibility = View.VISIBLE
            itemView.setOnClickListener {
                if (!checked.get(position, false)) {
                    checked.put(position, true)
                    imageOverlay.visibility = View.VISIBLE
                }
                else {
                    checked.put(position, false)
                    imageOverlay.visibility = View.INVISIBLE
                }
            }
        }

    }
}