package com.example.artka.myrecipestrackers.injection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.artka.myrecipestrackers.database.RecipeRoomDatabase
import com.example.artka.myrecipestrackers.viewmodels.SharedViewModel

class ViewModelFactory(private val activity: AppCompatActivity) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SharedViewModel::class.java)) {
            val db = Room.databaseBuilder(activity, RecipeRoomDatabase::class.java, "recipe.db")
                    .allowMainThreadQueries()
                    .build()
            @Suppress("UNCHECKED_CAST")
            return SharedViewModel(db.recipeDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}