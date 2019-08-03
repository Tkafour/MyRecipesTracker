package com.example.artka.myrecipestrackers.ui.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.artka.myrecipestrackers.R
import com.example.artka.myrecipestrackers.adapters.RecipeListAdapter
import com.example.artka.myrecipestrackers.databinding.RecipeFragmentListBinding
import com.example.artka.myrecipestrackers.retrofit.apiresponse.RecipeModel
import com.example.artka.myrecipestrackers.ui.fragments.RecipeListFragmentDirections.actionListToDetail
import com.example.artka.myrecipestrackers.utils.debugLog
import com.example.artka.myrecipestrackers.viewmodels.SharedViewModel
import kotlinx.android.synthetic.main.fl_item_list.*
import kotlinx.android.synthetic.main.fl_item_list.recipe_image
import kotlinx.android.synthetic.main.fl_item_list.view.*
import kotlinx.android.synthetic.main.recipe_fragment_list.*

class RecipeListFragment : Fragment() {

    private val recipeViewModel: SharedViewModel by lazy {
        ViewModelProviders.of(activity as AppCompatActivity).get(SharedViewModel::class.java)
    }

    private lateinit var binding: RecipeFragmentListBinding
    private var loading = true
    val recipeListAdapter = RecipeListAdapter { model -> onRecipeClicked(model) }
    private var ingredient = ""

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_activity_menu, menu)
        val menuItem = menu.findItem(R.id.search_view)
        val searchView = menuItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                debugLog("SearchView Clicked")
                recipeViewModel.clearRecipeList()
                recipeListAdapter.clearList()
                ingredient = query
                recipeViewModel.loadRecipes(ingredient)
                return false
            }
        })
        searchView.setOnCloseListener {
            recipeViewModel.clearRecipeList()
            recipeListAdapter.clearList()
            ingredient = "chicken"
            recipeViewModel.loadRecipes(ingredient)

            false
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.recipe_fragment_list, container, false)
        val layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = layoutManager
        binding.viewModel = recipeViewModel

        binding.recyclerView.adapter = recipeListAdapter
        recipeViewModel.getRecipeList().observe(activity as AppCompatActivity, Observer<ArrayList<RecipeModel>> {
            debugLog("$it")
            recipeListAdapter.updateRecipeList(it)
            loading = true
        })
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    var visibleItemCount: Int
                    var totalItemCount: Int
                    var pastVisibleItems: Int
                    layoutManager.let {
                        visibleItemCount = it.childCount
                        totalItemCount = it.itemCount
                        pastVisibleItems = it.findFirstCompletelyVisibleItemPosition()
                    }

                    if (loading) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            loading = false
                            if (ingredient.isEmpty()) {
                                ingredient = "chicken"
                            }
                            recipeViewModel.loadRecipes(ingredient = ingredient, from = "${recipeListAdapter.itemCount}", to = "${recipeListAdapter.itemCount + 30}")
                        }
                    }


                }
            }
        })
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun onRecipeClicked(model: RecipeModel) {
        recipeViewModel.recipe.value = model
        val extras = FragmentNavigatorExtras(
                binding.recyclerView.recipe_image to "imageView"
        )
        findNavController().navigate(R.id.action_list_to_detail, null, null, extras)
    }
}
