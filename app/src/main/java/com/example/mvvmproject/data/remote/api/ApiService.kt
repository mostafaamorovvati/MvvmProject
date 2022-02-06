package com.example.mvvmproject.data.remote.api

import com.example.mvvmproject.data.remote.model.Photo
import com.example.mvvmproject.data.remote.model.User
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("photos")
    suspend fun getPhotos(): Response<List<Photo>>

    @GET("users")
    suspend fun getUsers():Response<List<User>>

}