package com.example.artka.myrecipestrackers.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.artka.myrecipestrackers.R
import com.example.artka.myrecipestrackers.databinding.FdItemListBinding
import com.example.artka.myrecipestrackers.retrofit.apiresponse.RecipeModel
import com.example.artka.myrecipestrackers.utils.debugLog
import com.example.artka.myrecipestrackers.viewholder.RecipeDetailViewHolder

class RecipeDetailAdapter : RecyclerView.Adapter<RecipeDetailViewHolder>() {

  private lateinit var items: MutableList<String>

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeDetailViewHolder {
    val binding: FdItemListBinding = DataBindingUtil.inflate(
      LayoutInflater.from(parent.context),
      R.layout.fd_item_list,
      parent,
      false
    )
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

  fun updateRecipeList(pair: Pair<RecipeModel?, String?>) {
    when (pair.second) {
      "ingredients_text" -> this.items = pair.first?.ingredientLines as MutableList<String>
      "recipe_tags" -> this.items = pair.first?.healthLabels as MutableList<String>
      "nutrition" -> {
        pair.first.let {
          val fat = "Fat " + it?.totalNutrients?.FAT?.quantity?.div(it.totalDaily.FAT.quantity).toString()
          val protein = "Protein" + it?.totalNutrients?.P?.quantity?.div(it.totalDaily.P.quantity).toString()
          val carb = "Carbohydrates " + it?.totalNutrients?.CA?.quantity?.div(it.totalDaily.CA.quantity).toString()
          val cal = "Calories " + it?.totalNutrients?.ENERC_KCAL?.quantity?.div(it.totalDaily.ENERC_KCAL.quantity).toString()
          this.items = mutableListOf()
          this.items.add(fat)
          this.items.add(protein)
          this.items.add(carb)
          this.items.add(cal)
        }
      }
      else -> ""
    }
    debugLog("DetailAdapter updateRecipeList called with tag value $pair.second")
    notifyDataSetChanged()
  }
}