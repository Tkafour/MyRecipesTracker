package com.example.artka.myrecipestrackers.ui.recipedetailfragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.artka.myrecipestrackers.R
import com.example.artka.myrecipestrackers.databinding.RecipeFragmentDetailBinding
import com.example.artka.myrecipestrackers.viewmodels.SharedViewModel

class RecipeDetailFragment : Fragment() {

    private val recipeViewModel : SharedViewModel by lazy {
        ViewModelProviders.of(activity as AppCompatActivity).get(SharedViewModel::class.java)
    }

    private lateinit var binding : RecipeFragmentDetailBinding

    companion object {
        fun newInstance() : RecipeDetailFragment {
            return RecipeDetailFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.recipe_fragment_detail, container, false)
        binding.viewModel = recipeViewModel
        binding.recyclerViewDetail.layoutManager = LinearLayoutManager(activity)
        return binding.root
    }
}