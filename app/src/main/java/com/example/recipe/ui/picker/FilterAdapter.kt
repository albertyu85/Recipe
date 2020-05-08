package com.example.recipe.ui.picker

import android.util.Log
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe.R
import kotlinx.android.synthetic.main.list_item_meal_types.view.*

class FilterAdapter : RecyclerView.Adapter<FilterAdapter.ViewHolder>() {

    var data = listOf<String>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    val checked = android.util.SparseBooleanArray()

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_filter, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.title.text = item
        Log.d("Bind", "Binding $item")
        holder.bind(position)
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.list_filter_title)
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