package com.example.artka.myrecipestrackers.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.artka.myrecipestrackers.retrofit.apiresponse.RecipeModel

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(recipe : RecipeModel) : Long

    @Insert
    fun insertAll(vararg recipeModel: RecipeModel)

    @Query("DELETE FROM recipes")
    fun deleteAll()

    @Query("SELECT * from recipes ORDER BY id ASC")
    fun getAllRecipes() : LiveData<List<RecipeModel>>


}