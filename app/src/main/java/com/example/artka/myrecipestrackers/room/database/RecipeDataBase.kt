package com.example.artka.myrecipestrackers.room.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.artka.myrecipestrackers.room.RecipeDataDao
import com.example.artka.myrecipestrackers.room.RecipeModel

@Database(entities = [RecipeModel::class], version = 1)
abstract class RecipeDataBase : RoomDatabase() {
    abstract fun recipeDataDao(): RecipeDataDao
}