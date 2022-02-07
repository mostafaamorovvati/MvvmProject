package com.example.mvvmproject.ui.user

import com.example.mvvmproject.data.remote.model.User

class UserItemViewModel(
    private val user: User,
    private val mListener: OnItemClickListener
) {

    fun getName(): String {
        return user.name ?: ""
    }

    fun getUserName(): String {
        return user.username ?: ""
    }

    fun getEmail(): String {
        return user.email ?: ""
    }

    fun getAddress(): String {
        return "${user.address?.city} / ${user.address?.street} / ${user.address?.suite}"
    }

    fun getWebsite(): String {
        return user.website ?: ""
    }

    fun getPhone(): String {
        return user.phone ?: ""
    }

    fun getCompany(): String {
        return user.company?.name ?: ""
    }

    fun onItemClick() {
        mListener.onItemClick(user)
    }


    interface OnItemClickListener {
        fun onItemClick(item: User)
    }

}