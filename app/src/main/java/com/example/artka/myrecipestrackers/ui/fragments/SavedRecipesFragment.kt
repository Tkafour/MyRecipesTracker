package com.example.artka.myrecipestrackers.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.artka.myrecipestrackers.R
import com.example.artka.myrecipestrackers.adapters.RecipeListAdapter
import com.example.artka.myrecipestrackers.databinding.SavedRecipesFragmentBinding
import com.example.artka.myrecipestrackers.retrofit.apiresponse.RecipeModel
import com.example.artka.myrecipestrackers.ui.fragments.SavedRecipesFragmentDirections.actionSavedToDetail
import com.example.artka.myrecipestrackers.viewmodels.SharedViewModel

class SavedRecipesFragment : Fragment() {

    private val recipeViewModel: SharedViewModel by lazy {
        ViewModelProviders.of(activity as AppCompatActivity).get(SharedViewModel::class.java)
    }

    private lateinit var binding : SavedRecipesFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.saved_recipes_fragment, container, false)
        binding.recyclerView.layoutManager = GridLayoutManager(activity, 3)
        binding.viewModel = recipeViewModel


        val recipeListAdapter = RecipeListAdapter { recipeModel -> recipeViewModel.getDetailValues()}
        binding.recyclerView.adapter = recipeListAdapter
        recipeViewModel.getSavedList().observe(activity as AppCompatActivity, Observer<List<RecipeModel>> {
            recipeListAdapter.updateRecipeList(it)
        })
        return binding.root
    }

    private fun onRecipeClicked() {
        val navDirections = actionSavedToDetail()
        view?.let {
            findNavController(it).navigate(navDirections)
        }
    }
}