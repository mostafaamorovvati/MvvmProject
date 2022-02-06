package com.example.mvvmproject.ui.main

import com.example.mvvmproject.ui.base.BaseViewModel
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class MainViewModel : BaseViewModel<MainNavigator>() {

    fun openPhotoActivity() {
        getNavigator()?.openPhotoActivity()
    }

}