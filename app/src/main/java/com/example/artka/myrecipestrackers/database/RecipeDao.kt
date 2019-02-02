package com.example.artka.myrecipestrackers.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.artka.myrecipestrackers.retrofit.apiresponse.RecipeModel

@Dao
interface RecipeDao {

    @Insert
    fun insert(recipe : RecipeModel)

    @Insert
    fun insertAll(vararg recipeModel: RecipeModel)

    @Query("DELETE FROM recipe_table")
    fun deleteAll()

    @Query("SELECT * from recipe_table ORDER BY id ASC")
    fun getAllRecipes() : LiveData<List<RecipeModel>>


}