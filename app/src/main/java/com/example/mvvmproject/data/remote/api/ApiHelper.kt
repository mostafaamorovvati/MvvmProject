package com.example.mvvmproject.data.remote.api

import com.example.mvvmproject.data.remote.model.Photo
import com.example.mvvmproject.data.remote.model.User
import retrofit2.Response

interface ApiHelper {

    suspend fun getPhotos(): Response<List<Photo>>

    suspend fun getUsers():Response<List<User>>

}