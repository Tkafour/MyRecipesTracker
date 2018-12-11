package com.example.artka.myrecipestrackers.recipelistfragment

import com.example.artka.myrecipestrackers.base.BaseViewModel
import com.example.artka.myrecipestrackers.retrofit.apiresponse.*

class RecipeViewModel: BaseViewModel() {

    private lateinit var hit: Hit

    fun bind(hit: Hit) {
        this.hit = hit
    }

    fun getRecipe() : RecipeModel {
        return hit.recipe
    }
}