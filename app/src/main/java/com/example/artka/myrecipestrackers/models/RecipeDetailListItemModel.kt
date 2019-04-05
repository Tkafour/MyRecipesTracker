package com.example.artka.myrecipestrackers.models

class RecipeDetailListItemModel {

    private lateinit var recipeDetail: String

    fun bind(recipeDetail : String) {
        this.recipeDetail = recipeDetail
    }

    fun getRecipeDetail() : String {
        return recipeDetail
    }
}