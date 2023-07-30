package com.minhoi.coroutineflowpractice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecipeListAdapter : RecyclerView.Adapter<RecipeListAdapter.ViewHolder>() {

    private val list = mutableListOf<String>()

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val recipeName: TextView = view.findViewById(R.id.recipeName)

        fun bind(item : String) {
            recipeName.text = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recipe_name_item_row , parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun setList(lists : ArrayList<String>) {
        list.clear()
        list.addAll(lists)
        notifyDataSetChanged()
    }
}