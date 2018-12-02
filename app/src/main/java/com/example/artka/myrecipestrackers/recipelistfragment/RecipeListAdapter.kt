package com.example.artka.myrecipestrackers.recipelistfragment

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.artka.myrecipestrackers.R
import com.example.artka.myrecipestrackers.databinding.ListItemBinding
import com.example.artka.myrecipestrackers.room.RecipeModel
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class RecipeListAdapter: RecyclerView.Adapter<RecipeListAdapter.ViewHolder>() {

    private lateinit var items : List<RecipeModel>

    private val clickSubject = PublishSubject.create<RecipeModel>()

    val clickEvent : Observable<RecipeModel> = clickSubject

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding : ListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.list_item, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (::items.isInitialized) items.size else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun updateRecipeList(items : List<RecipeModel>){
        this.items = items
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        var viewModel = RecipeViewModel()

        init {
            itemView.setOnClickListener{
                clickSubject.onNext(items[layoutPosition])
            }
        }

        fun bind(recipeModel: RecipeModel) {
            viewModel.bind(recipeModel)
            binding.viewModel = viewModel
        }
    }
}