package com.example.artka.myrecipestrackers.models

import com.example.artka.myrecipestrackers.retrofit.apiresponse.*

class RecipeListModel {

    private lateinit var recipeModel: RecipeModel

    fun bind(recipeModel: RecipeModel) {
        this.recipeModel = recipeModel
    }

    fun getRecipeModel() : RecipeModel {
        return recipeModel
    }
}

