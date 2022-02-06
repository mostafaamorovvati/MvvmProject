package com.example.mvvmproject.utils

import com.blankj.utilcode.util.NetworkUtils

class NetworkHelper {

    fun isNetworkConnected(): Boolean {
        return NetworkUtils.isConnected()
    }

}