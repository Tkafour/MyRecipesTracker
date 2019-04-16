package com.example.artka.myrecipestrackers.utils

object Converters {

    @JvmStatic
    fun getFormattedWeight(weight : Double) : String {
        val kilograms = (weight / 1000).toInt()
        val grams = (weight - kilograms * 1000).toInt()
        return "$kilograms kg. $grams g."
    }

    @JvmStatic
    fun getFormattedCalories(calories : Double) : String {
        return "${calories.toInt()} Kcal"
    }

    @JvmStatic
    fun getFormattedTime(time : Double) : String {
        val hours = (time / 60).toInt()
        val minutes = (time - hours * 60).toInt()
        return "$hours h. $minutes m."
    }


}