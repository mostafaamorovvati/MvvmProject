package com.example.mvvmproject.ui.user

import com.example.mvvmproject.data.remote.model.User

class UserItemViewModel(val user: User) {

    fun getName(): String {
        return "NAME: " + user.name
    }

    fun getUserName(): String {
        return "USERNAME: " + user.username
    }

    fun getEmail(): String {
        return "EMAIL: " + user.email
    }

    fun getAddress(): String {
        return "ADDRESS: ${user.address.city} / ${user.address.street} / ${user.address.suite} / ${user.address.zipcode}"
    }

    fun getWebsite(): String {
        return "WEBSITE: " + user.website
    }

    fun getPhone(): String {
        return "PHONE: " + user.phone
    }

    fun getCompany(): String {
        return "COMPANY: " + user.company.name
    }

}