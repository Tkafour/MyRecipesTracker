package com.example.artka.myrecipestrackers.mainactivity


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import android.util.Log
import com.example.artka.myrecipestrackers.base.BaseViewModel
import com.example.artka.myrecipestrackers.recipelistfragment.RecipeListAdapter
import com.example.artka.myrecipestrackers.retrofit.RecipeApi
import com.example.artka.myrecipestrackers.retrofit.apiresponse.RecipeModel
import com.example.artka.myrecipestrackers.retrofit.apiresponse.RecipeModelWrapper
import com.example.artka.myrecipestrackers.utils.Enums
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SharedViewModel : BaseViewModel() {

    @Inject
    lateinit var recipeApi: RecipeApi

    val recipeListAdapter = RecipeListAdapter()

    private lateinit var subscriptionUrl: Disposable
    private var subscriptionAdapter: Disposable? = null

    private var State : MutableLiveData<Enums.State> = MutableLiveData()
    var recipe : MutableLiveData<RecipeModel> = MutableLiveData()
    private var searchText : MutableLiveData<String> = MutableLiveData()


    override fun onCleared() {
        super.onCleared()
        subscriptionUrl.dispose()
        subscriptionAdapter?.dispose()
    }

    init {
        State.value = Enums.State.MASTER
        loadRecipes()
        setupItemClick()
    }

    fun loadRecipes(ingredient : String = "chicken") {
        subscriptionUrl = recipeApi.getRecipes(ingredient)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {result -> onRetrieveRecipesListSuccess(result as RecipeModelWrapper)},
                        {onRetrieveRecipesListFailure()})
    }

    private fun onRetrieveRecipesListSuccess(wrapper: RecipeModelWrapper) {
        recipeListAdapter.updateRecipeList(wrapper.hits)
    }

    private fun onRetrieveRecipesListFailure() {
        Log.e("TAG", "Failed to get recipes")
    }

    private fun setupItemClick() {
        subscriptionAdapter = recipeListAdapter.clickEvent
                .subscribe {changeFragment(it)}
    }

    private fun changeFragment(recipeModel: RecipeModel) {

        recipe.value = recipeModel

        if (recipe.value != null) {
            State.value = Enums.State.DETAIL
            getState()
        }
    }

    fun getState() : LiveData<Enums.State> {
        return State
    }

    fun getRecipeModel() : LiveData<RecipeModel> {
        return recipe
    }

    fun getSearchText() : LiveData<String> {
        return searchText
    }

}
