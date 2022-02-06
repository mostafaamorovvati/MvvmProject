package com.example.mvvmproject.di

import com.example.mvvmproject.ui.photos.PhotoAdapter
import org.koin.dsl.module

val adapterModule = module {
    factory {
        PhotoAdapter()
    }
}