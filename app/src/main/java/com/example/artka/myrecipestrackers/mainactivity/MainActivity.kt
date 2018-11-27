package com.example.artka.myrecipestrackers.mainactivity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.artka.myrecipestrackers.R
import com.example.artka.myrecipestrackers.recipedetailfragment.RecipeDetailFragment
import com.example.artka.myrecipestrackers.recipelistfragment.RecipeListFragment
import com.example.artka.myrecipestrackers.recipelistfragment.RecipeListViewModel
import com.example.artka.myrecipestrackers.utils.inTransaction

class MainActivity : AppCompatActivity(), RecipeListViewModel.Callbacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            val fragment = RecipeListFragment()
            supportFragmentManager.inTransaction { add(R.id.fragment_container, fragment) }
        }

    }

    override fun itemClicked() {
        val fragment = RecipeDetailFragment()
        supportFragmentManager.inTransaction { replace(R.id.fragment_container, fragment) }
    }
}
