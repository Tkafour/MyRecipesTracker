package com.example.artka.myrecipestrackers.injection.component

import com.example.artka.myrecipestrackers.injection.module.NetworkModule
import com.example.artka.myrecipestrackers.models.RecipeDetailListItemModel
import com.example.artka.myrecipestrackers.viewmodels.SharedViewModel
import com.example.artka.myrecipestrackers.models.RecipeListModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(sharedViewModel: SharedViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(retrofitModule: NetworkModule): Builder
    }
}