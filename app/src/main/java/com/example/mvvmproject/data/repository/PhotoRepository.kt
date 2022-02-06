package com.example.mvvmproject.data.repository

import com.example.mvvmproject.data.remote.api.ApiHelper

class PhotoRepository(private val apiHelper: ApiHelper) {

    suspend fun getPhotos() = apiHelper.getPhotos()

}