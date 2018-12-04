package com.example.artka.myrecipestrackers.mainactivity

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.artka.myrecipestrackers.R
import com.example.artka.myrecipestrackers.databinding.ActivityMainBinding
import com.example.artka.myrecipestrackers.injection.ViewModelFactory
import com.example.artka.myrecipestrackers.recipedetailfragment.RecipeDetailFragment
import com.example.artka.myrecipestrackers.recipelistfragment.RecipeListFragment
import com.example.artka.myrecipestrackers.utils.Enums
import com.example.artka.myrecipestrackers.utils.inTransaction

class MainActivity : AppCompatActivity() {

    private val recipeViewModel: SharedViewModel by lazy {
        ViewModelProviders.of(this).get(SharedViewModel::class.java)
    }

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = recipeViewModel

        if (savedInstanceState == null) {
            val fragment = RecipeListFragment()
            supportFragmentManager.inTransaction { add(R.id.fragment_container, fragment).addToBackStack(null) }
        }

        binding.setLifecycleOwner(this)

        recipeViewModel.getState().observe(this, Observer<Enums.State> { it ->
            setupFragment(it!!)
        }
        )
    }

    private fun setupFragment(enumsState: Enums.State) {
        when (enumsState) {
            Enums.State.DETAIL -> {
                Log.e("TAG", "Opening Detail fragment")
                val fragment = RecipeDetailFragment.newInstance()
                supportFragmentManager.inTransaction { replace (R.id.fragment_container, fragment).addToBackStack(null) }
            }

            Enums.State.MASTER -> Log.e("TAG", "Opening Master fragment")
            else -> Log.e("TAG", "Failed to open fragment")
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount <= 1) {
            supportFinishAfterTransition()
        } else {
            supportFragmentManager.popBackStack()
        }
    }

}
