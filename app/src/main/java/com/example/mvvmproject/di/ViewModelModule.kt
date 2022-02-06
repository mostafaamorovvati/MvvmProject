package com.example.mvvmproject.di

import com.example.mvvmproject.ui.main.MainViewModel
import com.example.mvvmproject.ui.photos.PhotoViewModel
import com.example.mvvmproject.ui.user.UserViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.component.KoinApiExtension
import org.koin.dsl.module

@OptIn(KoinApiExtension::class)
val viewModelModule = module {

    viewModel {
        MainViewModel()
    }

    viewModel {
        UserViewModel(get(), get())
    }

    viewModel {
        PhotoViewModel(get(), get())
    }


}