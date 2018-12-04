package com.example.artka.myrecipestrackers.retrofit

import com.example.artka.myrecipestrackers.retrofit.apiresponse.RecipeModelWrapper
import io.reactivex.Observable
import retrofit2.http.GET

interface RecipeApi {

    @GET("/search?q=chicken&app_id=e96f638b&app_key=80fb54d3b3a0496d3488511ea0c2c0b9&from=0&to=3&calories=591-722&health=alcohol-free")
    fun getRecipes() : Observable<RecipeModelWrapper>
}