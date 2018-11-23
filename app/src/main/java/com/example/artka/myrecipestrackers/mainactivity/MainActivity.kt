package com.example.artka.myrecipestrackers.mainactivity

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.artka.myrecipestrackers.R
import com.example.artka.myrecipestrackers.databinding.MainActivityBinding
import com.example.artka.myrecipestrackers.injection.ViewModelFactory
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    private val recipeViewModel : RecipeListViewModel by lazy {
        ViewModelProviders.of(this, ViewModelFactory(this)).get(RecipeListViewModel::class.java)
    }

    private lateinit var binding : MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.viewModel = recipeViewModel
    }
}
