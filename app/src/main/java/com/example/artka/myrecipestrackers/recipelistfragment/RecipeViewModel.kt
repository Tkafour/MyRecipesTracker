package com.example.artka.myrecipestrackers.recipelistfragment

import com.example.artka.myrecipestrackers.base.BaseViewModel
import com.example.artka.myrecipestrackers.retrofit.apiresponse.*

class RecipeViewModel: BaseViewModel() {

    private lateinit var cautions: List<Any>
    private lateinit var dietLabels: List<String>
    private lateinit var digest: List<Digest>
    private lateinit var healthLabels: List<String>
    lateinit var image: String
    private lateinit var ingredientLines: List<String>
    private lateinit var ingredients: List<Ingredient>
    lateinit var label: String
    private lateinit var shareAs: String
    lateinit var source: String
    private lateinit var totalDaily: TotalDaily
    private lateinit var totalNutrients: TotalNutrients
    private lateinit var uri: String
    private lateinit var url: String

    var yield = Double
    var totalTime = Double
    var totalWeight = Double
    var calories = Double

    fun bind(recipeModel: RecipeModel) {
        cautions = recipeModel.cautions
        dietLabels = recipeModel.dietLabels
        digest = recipeModel.digest
        healthLabels = recipeModel.healthLabels
        image = recipeModel.image
        ingredientLines = recipeModel.ingredientLines
        ingredients = recipeModel.ingredients
        label = recipeModel.label
        shareAs = recipeModel.shareAs
        source = recipeModel.source
        totalDaily = recipeModel.totalDaily
        totalNutrients = recipeModel.totalNutrients
        uri = recipeModel.uri
        url = recipeModel.url

        var yield = recipeModel.yield
        var totalTime = recipeModel.totalTime
        var totalWeight = recipeModel.totalWeight
        var calories = recipeModel.calories
    }

    fun getRecipeTitle():String {
        return label
    }

    fun getImageUrl():String {
        return image
    }

}