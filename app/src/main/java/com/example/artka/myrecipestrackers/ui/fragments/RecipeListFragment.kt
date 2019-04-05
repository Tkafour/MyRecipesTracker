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
import androidx.recyclerview.widget.GridLayoutManager
import com.example.artka.myrecipestrackers.R
import com.example.artka.myrecipestrackers.adapters.RecipeListAdapter
import com.example.artka.myrecipestrackers.databinding.RecipeFragmentListBinding
import com.example.artka.myrecipestrackers.retrofit.apiresponse.RecipeModel
import com.example.artka.myrecipestrackers.ui.fragments.RecipeListFragmentDirections.actionListToDetail
import com.example.artka.myrecipestrackers.viewmodels.SharedViewModel

class RecipeListFragment : Fragment() {

    private val recipeViewModel: SharedViewModel by lazy {
        ViewModelProviders.of(activity as AppCompatActivity).get(SharedViewModel::class.java)
    }

    private lateinit var binding : RecipeFragmentListBinding

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_activity_menu, menu)
        val menuItem = menu?.findItem(R.id.search_view)
        val searchView = menuItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                Log.d("SearchView Tag", "SearchView Clicked")
                recipeViewModel.loadRecipes(query)
                return false
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.recipe_fragment_list, container, false)
        binding.recyclerView.layoutManager = GridLayoutManager(activity, 3)
        binding.viewModel = recipeViewModel


        val recipeListAdapter = RecipeListAdapter { model -> onRecipeClicked(model) }
        binding.recyclerView.adapter = recipeListAdapter
        recipeViewModel.getRecipeList().observe(activity as AppCompatActivity, Observer<ArrayList<RecipeModel>> {
            recipeListAdapter.updateRecipeList(it)
        })
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun onRecipeClicked(model : RecipeModel) {
        recipeViewModel.recipe.value = model
        val navDirections = actionListToDetail()
        view?.let {
            findNavController(it).navigate(navDirections)
        }
    }
}
