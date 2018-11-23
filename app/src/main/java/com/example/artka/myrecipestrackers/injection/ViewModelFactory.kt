package com.example.artka.myrecipestrackers.injection

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import com.example.artka.myrecipestrackers.mainactivity.RecipeListViewModel
import com.example.artka.myrecipestrackers.room.database.RecipeDataBase

class ViewModelFactory(private val activity: AppCompatActivity): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecipeListViewModel::class.java)) {
            val db = Room.databaseBuilder(activity.applicationContext, RecipeDataBase::class.java, "posts").build()
            @Suppress("UNCHECKED_CAST")
            return RecipeListViewModel(db.recipeDataDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}