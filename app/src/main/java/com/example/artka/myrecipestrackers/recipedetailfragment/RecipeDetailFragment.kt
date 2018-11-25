package com.example.artka.myrecipestrackers.recipedetailfragment

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.artka.myrecipestrackers.R
import com.example.artka.myrecipestrackers.databinding.RecipeDetailFragmentBinding
import com.example.artka.myrecipestrackers.injection.ViewModelFactory

class RecipeDetailFragment : Fragment() {

    private val recipeViewModel : RecipeDetailViewModel by lazy {
        ViewModelProviders.of(this, ViewModelFactory(activity as AppCompatActivity)).get(RecipeDetailViewModel::class.java)
    }

    private lateinit var binding : RecipeDetailFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.recipe_detail_fragment, container, false)
        binding.viewModel = recipeViewModel
        return binding.root
    }
}