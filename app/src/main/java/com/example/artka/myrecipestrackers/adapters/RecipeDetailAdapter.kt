package com.example.artka.myrecipestrackers.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.artka.myrecipestrackers.R
import com.example.artka.myrecipestrackers.databinding.FdItemListBinding
import com.example.artka.myrecipestrackers.retrofit.apiresponse.RecipeModel
import com.example.artka.myrecipestrackers.viewholder.RecipeDetailViewHolder

class RecipeDetailAdapter : RecyclerView.Adapter<RecipeDetailViewHolder>() {

    private lateinit var items: MutableList<String>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeDetailViewHolder {
        val binding: FdItemListBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.fd_item_list, parent, false)
        return RecipeDetailViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (::items.isInitialized) items.size else 0
    }

    override fun onBindViewHolder(holder: RecipeDetailViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun clearData() {
        if (::items.isInitialized) {
            if (items.isNotEmpty()) {
                items.clear()
            }
        }

    }

    fun updateRecipeList(pair : Pair<RecipeModel?, String?>) {
        when (pair.second) {
            "ingredients_text" -> this.items = pair.first?.ingredientLines as MutableList<String>
            "recipe_tags" -> this.items = pair.first?.healthLabels as MutableList<String>
            else -> ""
        }
        Log.d("TAG", "DetailAdapter updateRecipeList called with tag value $pair.second")
        notifyDataSetChanged()
    }
}