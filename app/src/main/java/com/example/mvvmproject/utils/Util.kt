package com.example.mvvmproject.utils

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.blankj.utilcode.util.NetworkUtils
import com.example.mvvmproject.R
import com.squareup.picasso.Picasso

@BindingAdapter("loadImage")
fun loadImage(view: ImageView, imageUrl: String?) {
    Picasso
        .get()
        .load(imageUrl)
        .placeholder(R.drawable.ic_launcher_background)
        .into(view)
}

fun isNetworkConnected(): Boolean {
    return NetworkUtils.isConnected()
}

fun log(message: String) {
    Log.d(LOG_TAG, message)
}