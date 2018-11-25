package com.example.artka.myrecipestrackers.recipelistfragment


import android.util.Log
import com.example.artka.myrecipestrackers.base.BaseViewModel
import com.example.artka.myrecipestrackers.retrofit.RecipeApi
import com.example.artka.myrecipestrackers.room.RecipeDataDao
import com.example.artka.myrecipestrackers.room.RecipeModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import kotlin.collections.ArrayList

class RecipeListViewModel(private val recipeDao: RecipeDataDao) : BaseViewModel(), IRecipeListViewModel {

    @Inject
    lateinit var recipeApi: RecipeApi
    val recipeListAdapter = RecipeListAdapter()

    private lateinit var subscription: Disposable

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    init {
        loadRecipes()
    }

    override fun loadRecipes() {
        subscription = Observable.fromCallable { recipeDao.getAll() }
                .concatMap { dbRecipeList ->
                    if (dbRecipeList.isEmpty())
                        recipeApi.getRecipes().concatMap { apiRecipeList ->
                            recipeDao.insertAll(*apiRecipeList.recipes.toTypedArray())
                            Observable.just(apiRecipeList)
                        }
                    else
                        Observable.just(dbRecipeList)

                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {result -> onRetrieveRecipesListSuccess(result as ArrayList<RecipeModel>)},
                        {onRetrieveRecipesListFailure()})
    }

    private fun onRetrieveRecipesListSuccess(recipesList: List<RecipeModel>) {
        recipeListAdapter.updateRecipeList(recipesList)
    }

    private fun onRetrieveRecipesListFailure() {
        Log.e("TAG", "Failed to get recipes")
    }

}


