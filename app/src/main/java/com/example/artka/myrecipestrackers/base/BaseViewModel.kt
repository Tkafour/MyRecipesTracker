package com.example.artka.myrecipestrackers.base

import androidx.lifecycle.ViewModel
import com.example.artka.myrecipestrackers.injection.component.DaggerViewModelInjector
import com.example.artka.myrecipestrackers.injection.module.NetworkModule
import com.example.artka.myrecipestrackers.injection.component.ViewModelInjector
import com.example.artka.myrecipestrackers.mainactivity.SharedViewModel
import com.example.artka.myrecipestrackers.recipelistfragment.RecipeViewModel

abstract class BaseViewModel: ViewModel(){

    private val injector: ViewModelInjector = DaggerViewModelInjector
            .builder()
            .networkModule(NetworkModule)
            .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is SharedViewModel -> injector.inject(this)
            is RecipeViewModel -> injector.inject(this)
        }
    }
}