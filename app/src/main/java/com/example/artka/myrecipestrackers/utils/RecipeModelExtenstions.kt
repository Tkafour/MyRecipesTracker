package com.example.artka.myrecipestrackers.utils

import com.example.artka.myrecipestrackers.retrofit.apiresponse.RecipeModel

fun RecipeModel.getFormattedWeight() : String {
    val weight = this.totalWeight
    val kilograms = (weight / 1000).toInt()
    val grams = (weight - kilograms * 1000).toInt()
    return "$kilograms kg. $grams g."
}

fun RecipeModel.getFormattedCalories() : String {
    val calories = this.calories.toInt()
    return "$calories Kcal"
}

fun RecipeModel.getFormattedTime() : String {
    val time = this.totalTime
    val hours = (time / 60).toInt()
    val minutes = (time - hours * 60).toInt()
    return "$hours h. $minutes m."
}