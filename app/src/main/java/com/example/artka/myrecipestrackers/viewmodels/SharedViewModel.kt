package com.example.artka.myrecipestrackers.viewmodels


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import android.util.Log
import android.view.View
import com.example.artka.myrecipestrackers.R
import com.example.artka.myrecipestrackers.base.BaseViewModel
import com.example.artka.myrecipestrackers.retrofit.RecipeApi
import com.example.artka.myrecipestrackers.retrofit.apiresponse.RecipeModel
import com.example.artka.myrecipestrackers.retrofit.apiresponse.RecipeModelWrapper
import com.example.artka.myrecipestrackers.database.RecipeDao
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SharedViewModel(private val recipeDao: RecipeDao) : BaseViewModel() {

    @Inject
    lateinit var recipeApi: RecipeApi

    private var detailValues: MutableLiveData<Pair<RecipeModel?, String?>> = MutableLiveData()

    var loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    private lateinit var subscriptionUrl: Disposable

    private var recipeList: MutableLiveData<ArrayList<RecipeModel>> = MutableLiveData()
    var recipe: MutableLiveData<RecipeModel> = MutableLiveData()
    private var recipeDbList : LiveData<List<RecipeModel>>



    override fun onCleared() {
        super.onCleared()
        subscriptionUrl.dispose()
    }

    init {
        loadRecipes()
        recipeDbList = recipeDao.getAllRecipes()
    }

    fun loadRecipes(ingredient: String = "chicken") {
        subscriptionUrl = recipeApi.getRecipes(ingredient)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrievePostListStart() }
                .doOnTerminate { onRetrievePostListFinish() }
                .subscribe({ wrapper ->
                    onRetrieveRecipesListSuccess(wrapper)
                },
                        { it ->
                            Log.d("TAG", it.toString())
                            onRetrieveRecipesListFailure()
                        })
    }

    private fun onRetrieveRecipesListSuccess(wrapper: RecipeModelWrapper) {
        onRetrievePostListFinish()
        recipeList.value?.clear()

        var list: ArrayList<RecipeModel> = ArrayList()

        Log.d("TAG", "Reached this point")

        for (i in 0.until(wrapper.hits.size)) {
            list.add(wrapper.hits[i].recipe)
            Log.d("TAG", i.toString())
        }
        Log.d("TAG", "Reached this point2")
        recipeList.value = list
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

    fun getRecipeModel(): LiveData<RecipeModel> {
        return recipe
    }

    fun getRecipeList(): LiveData<ArrayList<RecipeModel>> {
        return recipeList
    }

    fun getButtonClicked(view: View) {
        when (view.id) {
            R.id.ingredients_text -> {
                Log.d("TAG", "Ingredients Button clicked")
                detailValues.value = Pair(recipe.value, view.tag.toString())
            }
            R.id.basic_nutrition -> {
                Log.d("TAG", "Nutrition Button clicked")
                detailValues.value = Pair(recipe.value, view.tag.toString())
            }
            R.id.recipe_tags -> {
                Log.d("TAG", "Tags Button clicked")
                detailValues.value = Pair(recipe.value, view.tag.toString())
            }

            R.id.fab_add_item -> {
                recipeDao.insert(recipe.value ?: RecipeModel())

                Log.d("TAG", "FAB Clicked")
            }

            else -> {
                Log.d("TAG", "Something went wrong")
            }
        }
    }

    fun getDetailValues(): LiveData<Pair<RecipeModel?, String?>> {
        return detailValues
    }

    fun getSavedList() : LiveData<List<RecipeModel>> {
        return recipeDbList
    }
}
