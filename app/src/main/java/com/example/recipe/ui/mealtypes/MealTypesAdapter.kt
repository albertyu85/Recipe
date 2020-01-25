package com.example.recipe.ui.mealtypes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe.R
import kotlinx.android.synthetic.main.list_item_meal_types.view.*

class MealTypesAdapter(val listener : () -> Unit) : RecyclerView.Adapter<MealTypesAdapter.ViewHolder>() {

    var data = listOf<String>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context)
        val view = layout.inflate(R.layout.list_item_meal_types, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.title.text = item
        holder.bind(listener)
    }

    class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        val title = itemView.list_meal_type_title
        fun bind(clickListener : () -> Unit) {
            itemView.setOnClickListener {
                clickListener()
            }
        }

    }
}