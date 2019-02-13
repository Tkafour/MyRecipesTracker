package com.example.artka.myrecipestrackers.viewmodels

import com.example.artka.myrecipestrackers.base.BaseViewModel
import com.example.artka.myrecipestrackers.retrofit.apiresponse.*

class RecipeViewModel: BaseViewModel() {

    private lateinit var recipeModel: RecipeModel

    fun bind(recipeModel: RecipeModel) {
        this.recipeModel = recipeModel
    }

    fun getRecipe() : RecipeModel {
        return recipeModel
    }
}

