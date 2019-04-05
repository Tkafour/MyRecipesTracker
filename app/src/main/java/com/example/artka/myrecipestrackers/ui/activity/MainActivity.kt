package com.example.artka.myrecipestrackers.ui.activity

import androidx.lifecycle.ViewModelProviders
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.NavigationUI.*
import com.example.artka.myrecipestrackers.R
import com.example.artka.myrecipestrackers.databinding.ActivityMainBinding
import com.example.artka.myrecipestrackers.injection.ViewModelFactory
import com.example.artka.myrecipestrackers.viewmodels.SharedViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    public val TAG = MainActivity::class.java.toString()

    private val recipeViewModel: SharedViewModel by lazy {
        ViewModelProviders.of(this, ViewModelFactory(this)).get(SharedViewModel::class.java)
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = recipeViewModel

        binding.lifecycleOwner = this
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.collapseActionView()
        setupNavigation()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navigateUp(findNavController(this, R.id.nav_host_fragment), drawerLayout)
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun setupNavigation() {
        val navController = findNavController(this, R.id.nav_host_fragment)

        setupActionBarWithNavController(this, navController, drawerLayout)

        navigationView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            drawerLayout.closeDrawers()
            true
        }

        setupWithNavController(navigationView, navController)
    }

}
