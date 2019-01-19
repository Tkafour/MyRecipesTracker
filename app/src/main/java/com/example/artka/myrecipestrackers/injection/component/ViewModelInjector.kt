package com.example.artka.myrecipestrackers.injection.component

import com.example.artka.myrecipestrackers.injection.module.NetworkModule
import com.example.artka.myrecipestrackers.viewmodels.RecipeDetailViewModel
import com.example.artka.myrecipestrackers.viewmodels.SharedViewModel
import com.example.artka.myrecipestrackers.viewmodels.RecipeViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(sharedViewModel: SharedViewModel)

    fun inject(recipeViewModel: RecipeViewModel)

    fun inject(recipeDetailViewModel: RecipeDetailViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(retrofitModule: NetworkModule): Builder
    }
}