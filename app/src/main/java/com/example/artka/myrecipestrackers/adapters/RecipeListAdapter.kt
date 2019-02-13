package com.example.artka.myrecipestrackers.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.artka.myrecipestrackers.R
import com.example.artka.myrecipestrackers.databinding.FlItemListBinding
import com.example.artka.myrecipestrackers.retrofit.apiresponse.Hit
import com.example.artka.myrecipestrackers.retrofit.apiresponse.RecipeModel
import com.example.artka.myrecipestrackers.viewholder.RecipeViewHolder

class RecipeListAdapter(val clickListener: (RecipeModel) -> Unit): RecyclerView.Adapter<RecipeViewHolder>() {

    private lateinit var items : List<RecipeModel>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding : FlItemListBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.fl_item_list, parent, false)
        return RecipeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (::items.isInitialized) items.size else 0
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(items[position], clickListener)
    }

    fun updateRecipeList(items : List<RecipeModel>){
        this.items = items
        notifyDataSetChanged()
    }


}