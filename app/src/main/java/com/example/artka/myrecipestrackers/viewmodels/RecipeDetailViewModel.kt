package com.example.artka.myrecipestrackers.viewmodels

import com.example.artka.myrecipestrackers.base.BaseViewModel

class RecipeDetailViewModel : BaseViewModel() {

    private lateinit var recipeDetail: String

    fun bind(recipeDetail : String) {
        this.recipeDetail = recipeDetail
    }

    fun getRecipeDetail() : String {
        return recipeDetail
    }
}