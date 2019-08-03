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
import com.example.artka.myrecipestrackers.utils.debugLog
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class SharedViewModel(private val recipeDao: RecipeDao) : BaseViewModel() {

    @Inject
    lateinit var recipeApi: RecipeApi

    private lateinit var subscriptionUrl: Disposable

    private var detailValues: MutableLiveData<Pair<RecipeModel?, String?>> = MutableLiveData()
    var loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    private var recipeList: MutableLiveData<ArrayList<RecipeModel>> = MutableLiveData()
    var recipe: MutableLiveData<RecipeModel> = MutableLiveData()
    private var recipeDbList: LiveData<List<RecipeModel>>


    override fun onCleared() {
        super.onCleared()
        subscriptionUrl.dispose()
    }

    init {
        recipeList.value = ArrayList()
        loadRecipes()
        recipeDbList = recipeDao.getAllRecipes()
    }

    fun loadRecipes(ingredient: String = "chicken", from: String = "0", to: String = "30") {
        subscriptionUrl = recipeApi.getRecipes(ingredient = ingredient, start = from, end = to)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrievePostListStart() }
                .doOnTerminate { onRetrievePostListFinish() }
                .subscribe({ wrapper ->
                    onRetrieveRecipesListSuccess(wrapper)
                },
                        {
                            it.printStackTrace()
                            onRetrieveRecipesListFailure(it)
                        })
    }

    private fun onRetrieveRecipesListSuccess(wrapper: RecipeModelWrapper) {
        onRetrievePostListFinish()
        recipeList.value?.clear()

        val list: ArrayList<RecipeModel> = ArrayList()

        for (i in 0.until(wrapper.hits.size)) {
            list.add(wrapper.hits[i].recipe)
        }
        debugLog("${list.size}")
        debugLog("${recipeList.value}")
        recipeList.value?.addAll(list)
        recipeList.postValue(recipeList.value)
        debugLog("${recipeList.value}")
    }

    private fun onRetrieveRecipesListFailure(throwable: Throwable) {
        debugLog(throwable.toString())
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
            R.id.fab_add_item -> {
                recipeDao.insert(recipe.value ?: RecipeModel())
            }

            else -> {
                detailValues.value = Pair(recipe.value, view.tag.toString())
            }
        }
    }

    fun getDetailValues(): LiveData<Pair<RecipeModel?, String?>> {
        return detailValues
    }

    fun getSavedList(): LiveData<List<RecipeModel>> {
        return recipeDbList
    }

    fun clearRecipeList() {
        recipeList.value?.clear()
    }
}
