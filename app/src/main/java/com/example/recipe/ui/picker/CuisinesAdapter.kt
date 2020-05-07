package com.example.recipe.ui.picker

import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe.R
import kotlinx.android.synthetic.main.list_item_meal_types.view.*

class CuisinesAdapter() : RecyclerView.Adapter<CuisinesAdapter.ViewHolder>() {
    var data = listOf<String>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    val checked = SparseBooleanArray()

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

        holder.bind(position)
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val cuisineName = itemView.findViewById<TextView>(R.id.list_cuisine_title)
        val imageOverlay: ImageView = itemView.image_view_overlay
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
