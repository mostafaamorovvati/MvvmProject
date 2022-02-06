package com.example.mvvmproject.data.remote.api

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getPhotos() = apiService.getPhotos()

}