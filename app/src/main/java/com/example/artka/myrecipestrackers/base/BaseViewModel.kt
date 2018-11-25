package com.example.artka.myrecipestrackers.base

import android.arch.lifecycle.ViewModel
import com.example.artka.myrecipestrackers.injection.component.DaggerViewModelInjector
import com.example.artka.myrecipestrackers.injection.module.NetworkModule
import com.example.artka.myrecipestrackers.injection.component.ViewModelInjector
import com.example.artka.myrecipestrackers.recipelistfragment.RecipeListViewModel
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
            is RecipeListViewModel -> injector.inject(this)
            is RecipeViewModel -> injector.inject(this)
        }
    }
}