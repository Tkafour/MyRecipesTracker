package com.example.artka.myrecipestrackers.ui.mainactivity

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import com.example.artka.myrecipestrackers.R
import com.example.artka.myrecipestrackers.databinding.ActivityMainBinding
import com.example.artka.myrecipestrackers.injection.ViewModelFactory
import com.example.artka.myrecipestrackers.ui.recipedetailfragment.RecipeDetailFragment
import com.example.artka.myrecipestrackers.ui.recipelistfragment.RecipeListFragment
import com.example.artka.myrecipestrackers.ui.webviewfragment.WebViewFragment
import com.example.artka.myrecipestrackers.utils.Enums
import com.example.artka.myrecipestrackers.utils.inTransaction
import com.example.artka.myrecipestrackers.viewmodels.SharedViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    public val TAG = MainActivity::class.java.toString()

    private val recipeViewModel: SharedViewModel by lazy {
        ViewModelProviders.of(this, ViewModelFactory(this)).get(SharedViewModel::class.java)
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_activity_menu, menu)
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
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = recipeViewModel

        if (savedInstanceState == null) {
            val fragment = RecipeListFragment.newInstance()
            supportFragmentManager.inTransaction { add(R.id.fragment_container, fragment).addToBackStack(null) }
        }
        binding.setLifecycleOwner(this)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        recipeViewModel.getState().observe(this, Observer<Enums.State> { it ->
            setupFragment(it!!)
        }
        )
    }


    private fun setupFragment(enumsState: Enums.State) {
        when (enumsState) {
            Enums.State.DETAIL -> {
                supportActionBar?.hide()
                val fragment = RecipeDetailFragment.newInstance()
                supportFragmentManager.inTransaction { replace(R.id.fragment_container, fragment).addToBackStack(null) }
            }

            Enums.State.MASTER -> {
                if (supportFragmentManager.backStackEntryCount == 2) {
                    supportActionBar?.show()
                }
            }
            Enums.State.WEBVIEW -> {
                supportActionBar?.hide()
                val fragment = WebViewFragment.newInstance()
                supportFragmentManager.inTransaction { replace(R.id.fragment_container, fragment).addToBackStack(null) }
            }
            else -> Log.d(TAG, "Failed to open fragment")
        }
    }

    override fun onBackPressed() {
        Log.d(TAG, "BackStackEntryCount: " + supportFragmentManager.backStackEntryCount)
        if (supportFragmentManager.backStackEntryCount == 2) {
            supportFragmentManager.popBackStack()
            setupFragment(Enums.State.MASTER)
        } else if (supportFragmentManager.backStackEntryCount == 1) {
            supportFinishAfterTransition()
        } else {
            supportFragmentManager.popBackStack()
        }
    }

}
