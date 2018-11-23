package com.example.artka.myrecipestrackers.mainactivity

import android.arch.lifecycle.LiveData
import com.example.artka.myrecipestrackers.room.RecipeModel

interface IRecipeListViewModel {
    fun loadRecipes()
}