package com.example.artka.myrecipestrackers.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.artka.myrecipestrackers.databinding.FlItemListBinding
import com.example.artka.myrecipestrackers.retrofit.apiresponse.RecipeModel
import com.example.artka.myrecipestrackers.models.RecipeListModel

class RecipeViewHolder(private val binding: FlItemListBinding) : RecyclerView.ViewHolder(binding.root) {

    var viewModel = RecipeListModel()

    fun bind(recipe: RecipeModel, clickListener : (RecipeModel) -> Unit) {
        viewModel.bind(recipe)
        binding.viewModel = viewModel
        itemView.setOnClickListener{clickListener(recipe)}
    }
}