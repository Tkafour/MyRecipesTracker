package com.example.artka.myrecipestrackers.retrofit

import com.example.artka.myrecipestrackers.retrofit.apiresponse.RecipeModelWrapper
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApi {

    @GET("/search")
    fun getRecipes(@Query("q") ingredient : String = "chicken",
                   @Query("app_id") appId : String = "e96f638b",
                   @Query("app_key") appKey : String = "80fb54d3b3a0496d3488511ea0c2c0b9",
                   @Query("from") start : String = "0",
                   @Query("to") end : String = "30",
                   @Query ("calories") calories : String = "500-700",
                   @Query ("health") health : String = "alcohol-free"): Observable<RecipeModelWrapper>

}