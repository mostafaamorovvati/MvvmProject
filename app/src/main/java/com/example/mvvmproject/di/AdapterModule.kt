package com.example.mvvmproject.di

import com.example.mvvmproject.ui.photos.PhotoAdapter
import com.example.mvvmproject.ui.user.UserAdapter
import org.koin.dsl.module

val adapterModule = module {

    factory {
        PhotoAdapter()
    }

    factory {
        UserAdapter()
    }
}