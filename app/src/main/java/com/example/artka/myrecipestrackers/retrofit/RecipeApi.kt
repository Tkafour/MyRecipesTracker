package com.example.artka.myrecipestrackers.retrofit

import com.example.artka.myrecipestrackers.room.RecipeModel
import com.example.artka.myrecipestrackers.room.RecipeModelWrapper
import io.reactivex.Observable
import retrofit2.http.GET

interface RecipeApi {

    @GET("/api/search?key=e759dbb325c0dff8db93e65f1d737113&q=chicken breast&page=2 ")
    fun getRecipes() : Observable<RecipeModelWrapper>
}