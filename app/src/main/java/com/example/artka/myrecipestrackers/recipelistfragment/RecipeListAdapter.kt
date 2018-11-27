package com.example.artka.myrecipestrackers.recipelistfragment

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.artka.myrecipestrackers.R
import com.example.artka.myrecipestrackers.databinding.ListItemBinding
import com.example.artka.myrecipestrackers.room.RecipeModel

class RecipeListAdapter(val clickListener: (RecipeModel) -> Unit): RecyclerView.Adapter<RecipeListAdapter.ViewHolder>() {

    private lateinit var items : List<RecipeModel>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding : ListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.list_item, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (::items.isInitialized) items.size else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], clickListener)

    fun updateRecipeList(items : List<RecipeModel>){
        this.items = items
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        private val viewModel = RecipeViewModel()

        fun bind(recipeModel: RecipeModel, clickListener: (RecipeModel) -> Unit) {
            viewModel.bind(recipeModel)
            binding.viewModel = viewModel
            binding.root.setOnClickListener{clickListener(recipeModel)}
        }
    }
}