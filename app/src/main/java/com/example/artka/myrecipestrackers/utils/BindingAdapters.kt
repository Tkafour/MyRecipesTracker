package com.example.artka.myrecipestrackers.utils

import android.view.View
import android.webkit.WebView
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.artka.myrecipestrackers.views.MyChromeViewClient
import com.example.artka.myrecipestrackers.views.MyWebViewClient
import com.squareup.picasso.Picasso

@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, imageUrl: String?) {
    Picasso.get()
            .load(imageUrl)
            .into(view)
}

@BindingAdapter("webView")
fun loadUrl(view : WebView, url : String? ) {
    view.webViewClient = MyWebViewClient()
    view.webChromeClient = MyChromeViewClient()
    view.settings.javaScriptEnabled = true
    view.loadUrl(url)
}

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { value -> view.visibility = value?:View.VISIBLE})
    }
}
