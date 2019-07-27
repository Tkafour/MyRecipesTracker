package com.example.artka.myrecipestrackers.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.artka.myrecipestrackers.R
import com.example.artka.myrecipestrackers.databinding.FdItemListBinding
import com.example.artka.myrecipestrackers.models.RecipeDetailListItemModel

class RecipeDetailViewHolder (private val binding: FdItemListBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    var viewModel = RecipeDetailListItemModel()

    fun bind(recipeDetail: String) {
        val resources = itemView.context.resources
        viewModel.bind(recipeDetail)
        binding.viewModel = viewModel
//        if (adapterPosition % 2 == 0) {
//            itemView.background.setTint(resources.getColor(R.color.light_gray))
//        } else {
//            itemView.background.setTint(resources.getColor(R.color.white))
//        }
    }

    override fun onClick(v: View?) {

    }
}