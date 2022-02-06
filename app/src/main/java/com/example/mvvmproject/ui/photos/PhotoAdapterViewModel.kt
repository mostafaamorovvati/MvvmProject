package com.example.mvvmproject.ui.photos

import com.example.mvvmproject.data.remote.model.Photo

class PhotoAdapterViewModel(
    private val item: Photo
) {

    fun getPhotoImageUrl(): String {
        return item.url
    }

    fun getTitle(): String {
        return item.title
    }
}