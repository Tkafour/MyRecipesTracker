package com.example.artka.myrecipestrackers.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.artka.myrecipestrackers.databinding.FdItemListBinding
import com.example.artka.myrecipestrackers.viewmodels.RecipeDetailViewModel

class RecipeDetailViewHolder (private val binding: FdItemListBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    var viewModel = RecipeDetailViewModel()

    fun bind(recipeDetail: String) {
        viewModel.bind(recipeDetail)
        binding.viewModel = viewModel
    }

    override fun onClick(v: View?) {

    }
}