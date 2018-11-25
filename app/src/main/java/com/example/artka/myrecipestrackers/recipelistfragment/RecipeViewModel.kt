package com.example.artka.myrecipestrackers.recipelistfragment

import android.arch.lifecycle.MutableLiveData
import android.databinding.BindingAdapter
import android.widget.ImageView
import com.example.artka.myrecipestrackers.base.BaseViewModel
import com.example.artka.myrecipestrackers.room.RecipeModel
import com.squareup.picasso.Picasso

class RecipeViewModel: BaseViewModel() {

    private val publisher = MutableLiveData<String>()
    private val f2fUrl = MutableLiveData<String>()
    private val title = MutableLiveData<String>()
    private val sourceUrl = MutableLiveData<String>()
    private val recipeId = MutableLiveData<String>()
    private var imageUrl = String()
    private val socialRank = MutableLiveData<Double>()
    private val publisherUrl = MutableLiveData<String>()

    fun bind(recipeModel: RecipeModel) {
        publisher.value = recipeModel.publisher
        f2fUrl.value = recipeModel.f2f_url
        title.value = recipeModel.title
        sourceUrl.value = recipeModel.source_url
        recipeId.value = recipeModel.recipe_id
        imageUrl = recipeModel.image_url
        socialRank.value = recipeModel.social_rank
        publisherUrl.value = recipeModel.publisher_url
    }

    fun getRecipeTitle():MutableLiveData<String> {
        return title
    }

    fun getImageUrl():String {
        return imageUrl
    }

}