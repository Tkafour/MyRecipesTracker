package com.example.artka.myrecipestrackers.ui.recipelistfragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.artka.myrecipestrackers.R
import com.example.artka.myrecipestrackers.adapters.RecipeListAdapter
import com.example.artka.myrecipestrackers.databinding.RecipeFragmentListBinding
import com.example.artka.myrecipestrackers.retrofit.apiresponse.RecipeModel
import com.example.artka.myrecipestrackers.viewmodels.SharedViewModel
import kotlinx.android.synthetic.main.recipe_fragment_list.*

class RecipeListFragment : Fragment() {

    private val recipeViewModel: SharedViewModel by lazy {
        ViewModelProviders.of(activity as AppCompatActivity).get(SharedViewModel::class.java)
    }

    private lateinit var binding : RecipeFragmentListBinding

    companion object {
        fun newInstance() : RecipeListFragment {
            return RecipeListFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.recipe_fragment_list, container, false)
        binding.recyclerView.layoutManager = GridLayoutManager(activity, 3)
        binding.viewModel = recipeViewModel


        val recipeListAdapter = RecipeListAdapter { recipeModel -> recipeViewModel.recipeItemClicked(recipeModel) }
        binding.recyclerView.adapter = recipeListAdapter
        recipeViewModel.getRecipeList().observe(activity as AppCompatActivity, Observer<ArrayList<RecipeModel>> {
            recipeListAdapter.updateRecipeList(it)
        })
        return binding.root
    }
}
