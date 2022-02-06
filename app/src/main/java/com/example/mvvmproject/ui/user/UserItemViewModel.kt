package com.example.mvvmproject.ui.user

import com.example.mvvmproject.data.remote.model.User

class UserItemViewModel(
    private val user: User,
    private val mListener: OnItemClickListener
) {

    fun getName(): String {
        return "NAME: ${user.name}"
    }

    fun getUserName(): String {
        return "USERNAME: ${user.username}"
    }

    fun getEmail(): String {
        return "EMAIL: ${user.email}"
    }

    fun getAddress(): String {
        return "ADDRESS: ${user.address?.city} / ${user.address?.street} / ${user.address?.suite}"
    }

    fun getWebsite(): String {
        return "WEBSITE: ${user.website}"
    }

    fun getPhone(): String {
        return "PHONE: ${user.phone}"
    }

    fun getCompany(): String {
        return "COMPANY: ${user.company?.name}"
    }

    fun onItemClick() {
        mListener.onItemClick(user)
    }


    interface OnItemClickListener {
        fun onItemClick(item: User)
    }

}