package com.example.artka.myrecipestrackers.viewmodels


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import android.util.Log
import android.view.View
import com.example.artka.myrecipestrackers.R
import com.example.artka.myrecipestrackers.adapters.RecipeDetailAdapter
import com.example.artka.myrecipestrackers.base.BaseViewModel
import com.example.artka.myrecipestrackers.retrofit.RecipeApi
import com.example.artka.myrecipestrackers.retrofit.apiresponse.RecipeModel
import com.example.artka.myrecipestrackers.retrofit.apiresponse.RecipeModelWrapper
import com.example.artka.myrecipestrackers.adapters.RecipeListAdapter
import com.example.artka.myrecipestrackers.database.RecipeDao
import com.example.artka.myrecipestrackers.utils.Enums
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SharedViewModel(private val recipeDao: RecipeDao) : BaseViewModel() {

    @Inject
    lateinit var recipeApi: RecipeApi

    val recipeListAdapter = RecipeListAdapter { recipeModel -> recipeItemClicked(recipeModel) }
    val recipeDetailAdapter = RecipeDetailAdapter()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    private lateinit var subscriptionUrl: Disposable

    private var fragmentState: MutableLiveData<Enums.State> = MutableLiveData()
    private var recipeList: ArrayList<RecipeModel> = ArrayList()
    private var recipe: MutableLiveData<RecipeModel> = MutableLiveData()


    override fun onCleared() {
        super.onCleared()
        subscriptionUrl.dispose()
    }

    init {
        fragmentState.value = Enums.State.MASTER
        loadRecipes()
    }

    fun loadRecipes(ingredient: String = "chicken") {
        subscriptionUrl = recipeApi.getRecipes(ingredient)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrievePostListStart() }
                .doOnTerminate { onRetrievePostListFinish() }
                .subscribe({ wrapper ->
                    onRetrieveRecipesListSuccess(wrapper) },
                        {onRetrieveRecipesListFailure()})
    }

    private fun onRetrieveRecipesListSuccess(wrapper: RecipeModelWrapper) {
        onRetrievePostListFinish()
        recipeList.clear()

        Log.d("TAG", "Reached this point")

        for (i in 0.rangeTo(wrapper.hits.size)) {
            recipeList.add(wrapper.hits[i].recipe)
            Log.d("TAG", i.toString())
            if (i == wrapper.hits.size - 1) {
                recipeListAdapter.updateRecipeList(recipeList as List<RecipeModel>)
            }
        }
        Log.d("TAG", "Reached this point2")
    }

    private fun onRetrieveRecipesListFailure() {
        Log.d("TAG", "Failed to get recipes")
    }

    private fun onRetrievePostListStart() {
        loadingVisibility.value = View.VISIBLE
    }

    private fun onRetrievePostListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun switchToDetailFragment(recipeModel: RecipeModel) {

        recipeDetailAdapter.clearData()
        recipe.value = recipeModel

        fragmentState.value = Enums.State.DETAIL
        getState()

    }

    fun getState(): LiveData<Enums.State> {
        return fragmentState
    }

    fun getRecipeModel(): LiveData<RecipeModel> {
        return recipe
    }

    private fun recipeItemClicked(recipeModel: RecipeModel) {
        switchToDetailFragment(recipeModel)
    }

    fun getButtonClicked(view: View) {
        when (view.id) {
            R.id.ingredients_text -> {
                recipeDetailAdapter.updateRecipeList(recipe.value!!, view.tag.toString())
                Log.d("TAG", "Ingredients Button clicked")
            }
            R.id.basic_nutrition -> Log.d("TAG", "Nutrition Button clicked")
            R.id.mineral_nutrion -> Log.d("TAG", "Mineral Button clicked")
            R.id.recipe_tags -> {
                recipeDetailAdapter.updateRecipeList(recipe.value!!, view.tag.toString())
                Log.d("TAG", "Tags Button clicked")
            }
            R.id.recipe_site_url -> fragmentState.value = Enums.State.WEBVIEW
            else -> Log.d("TAG", "Something went wrong")
        }
    }

}
