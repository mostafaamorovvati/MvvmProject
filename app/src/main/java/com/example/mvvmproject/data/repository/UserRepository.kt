package com.example.mvvmproject.data.repository

import com.example.mvvmproject.data.remote.api.ApiHelper

class UserRepository(private val apiHelper: ApiHelper) {

    suspend fun getUsers() = apiHelper.getUsers()

}