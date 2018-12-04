package com.example.artka.myrecipestrackers.retrofit.apiresponse

data class Hit(
    val bookmarked: Boolean,
    val bought: Boolean,
    val recipe: RecipeModel
)