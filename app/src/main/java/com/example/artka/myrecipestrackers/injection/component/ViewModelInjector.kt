package com.example.artka.myrecipestrackers.injection.component

import com.example.artka.myrecipestrackers.injection.module.NetworkModule
import com.example.artka.myrecipestrackers.recipelistfragment.RecipeListViewModel
import com.example.artka.myrecipestrackers.recipelistfragment.RecipeViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(recipeListViewModel: RecipeListViewModel)

    fun inject(recipeViewModel: RecipeViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(retrofitModule: NetworkModule): Builder
    }
}