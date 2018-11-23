package com.example.artka.myrecipestrackers.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query

@Dao
interface RecipeDataDao {
    @Query("SELECT * from recipemodel")
    fun getAll() : List<RecipeModel>

    @Insert(onConflict = REPLACE)
    fun insertAll(vararg recipes : RecipeModel)

    @Query("DELETE from recipemodel")
    fun deleteAll()
}