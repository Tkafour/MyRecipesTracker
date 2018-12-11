package com.example.artka.myrecipestrackers.recipelistfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.artka.myrecipestrackers.R
import com.example.artka.myrecipestrackers.databinding.ItemListBinding
import com.example.artka.myrecipestrackers.retrofit.apiresponse.Hit
import com.example.artka.myrecipestrackers.retrofit.apiresponse.RecipeModel
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class RecipeListAdapter: RecyclerView.Adapter<RecipeListAdapter.ViewHolder>() {

    private lateinit var items : List<Hit>

    private val clickSubject = PublishSubject.create<Hit>()

    val clickEvent : Observable<Hit> = clickSubject

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding : ItemListBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_list, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (::items.isInitialized) items.size else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun updateRecipeList(items : List<Hit>){
        this.items = items
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {

        var viewModel = RecipeViewModel()

        init {
            itemView.setOnClickListener{
                clickSubject.onNext(items[layoutPosition])
            }
        }

        fun bind(hit: Hit) {
            viewModel.bind(hit)
            binding.viewModel = viewModel
        }
    }
}