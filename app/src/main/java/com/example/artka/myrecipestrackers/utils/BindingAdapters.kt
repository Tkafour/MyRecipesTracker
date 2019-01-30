package com.example.artka.myrecipestrackers.utils

import android.webkit.WebView
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
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
    view.loadUrl(url)
}
