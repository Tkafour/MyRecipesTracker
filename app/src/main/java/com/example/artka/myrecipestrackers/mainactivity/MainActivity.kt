package com.example.artka.myrecipestrackers.mainactivity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.artka.myrecipestrackers.R
import com.example.artka.myrecipestrackers.databinding.MainActivityBinding
import com.example.artka.myrecipestrackers.injection.ViewModelFactory
import com.example.artka.myrecipestrackers.recipedetailfragment.RecipeDetailFragment
import com.example.artka.myrecipestrackers.recipelistfragment.RecipeListFragment
import com.example.artka.myrecipestrackers.utils.Enums
import com.example.artka.myrecipestrackers.utils.inTransaction
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    private val recipeViewModel: SharedViewModel by lazy {
        ViewModelProviders.of(this, ViewModelFactory(this)).get(SharedViewModel::class.java)
    }

    lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)
        binding.viewModel = recipeViewModel

        if (savedInstanceState == null) {
            val fragment = RecipeListFragment()
            supportFragmentManager.inTransaction { add(R.id.fragment_container, fragment).addToBackStack(null) }
        }

        binding.setLifecycleOwner(this)

        recipeViewModel.getState().observe(this, Observer<Enums.state> { it ->
            setupFragment(it!!)
        }
        )
    }

    fun setupFragment(enumsState: Enums.state) {
        when (enumsState) {
            Enums.state.DETAIL -> {
                Log.e("TAG", "Opening Detail fragment")
                val fragment = RecipeDetailFragment.newInstance()
                supportFragmentManager.inTransaction { replace (R.id.fragment_container, fragment).addToBackStack(null) }
            }

            Enums.state.MASTER -> Log.e("TAG", "Opening Master fragment")
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
