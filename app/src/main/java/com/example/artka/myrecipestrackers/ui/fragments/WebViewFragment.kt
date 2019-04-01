package com.example.artka.myrecipestrackers.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.artka.myrecipestrackers.R
import com.example.artka.myrecipestrackers.databinding.WebviewFragmentBinding
import com.example.artka.myrecipestrackers.viewmodels.SharedViewModel

class WebViewFragment : Fragment() {

    private val recipeViewModel: SharedViewModel by lazy {
        ViewModelProviders.of(activity as AppCompatActivity).get(SharedViewModel::class.java)
    }

    private lateinit var binding : WebviewFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.webview_fragment, container, false)
        binding.viewModel = recipeViewModel
        return binding.root
    }

}