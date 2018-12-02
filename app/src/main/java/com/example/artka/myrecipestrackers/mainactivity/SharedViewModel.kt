package com.example.artka.myrecipestrackers.mainactivity


import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.artka.myrecipestrackers.base.BaseViewModel
import com.example.artka.myrecipestrackers.recipelistfragment.RecipeListAdapter
import com.example.artka.myrecipestrackers.retrofit.RecipeApi
import com.example.artka.myrecipestrackers.room.RecipeDataDao
import com.example.artka.myrecipestrackers.room.RecipeModel
import com.example.artka.myrecipestrackers.utils.Enums
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import kotlin.collections.ArrayList

class SharedViewModel(private val recipeDao: RecipeDataDao) : BaseViewModel(), IRecipeListViewModel {

    @Inject
    lateinit var recipeApi: RecipeApi
    val recipeListAdapter = RecipeListAdapter()

    private lateinit var subscriptionUrl: Disposable
    private var subscriptionAdapter: Disposable? = null

    private var state : MutableLiveData<Enums.state> = MutableLiveData()
    private var recipe : MutableLiveData<RecipeModel> = MutableLiveData()


    override fun onCleared() {
        super.onCleared()
        subscriptionUrl.dispose()
        subscriptionAdapter?.dispose()
    }

    init {
        state.value = Enums.state.MASTER
        loadRecipes()
        setupItemClick()
    }

    override fun loadRecipes() {
        subscriptionUrl = Observable.fromCallable { recipeDao.getAll() }
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

    private fun onRetrieveRecipesListSuccess(recipesList: ArrayList<RecipeModel>) {
        recipeListAdapter.updateRecipeList(recipesList)
    }

    private fun onRetrieveRecipesListFailure() {
        Log.e("TAG", "Failed to get recipes")
    }

    fun setupItemClick() {
        subscriptionAdapter = recipeListAdapter.clickEvent
                .subscribe {changeFragment(it)}
    }

    fun changeFragment(recipeModel: RecipeModel) {

        recipe.value = recipeModel

        if (recipe.value != null) {
            state.value = Enums.state.DETAIL
            getState()
        }
    }

    fun getState() : LiveData<Enums.state> {
        return state
    }

    fun getRecipe() : LiveData<RecipeModel> {
        return recipe
    }

}
