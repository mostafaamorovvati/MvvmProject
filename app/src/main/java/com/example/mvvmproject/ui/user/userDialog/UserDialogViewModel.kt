package com.example.mvvmproject.ui.user.userDialog

import com.example.mvvmproject.ui.base.BaseViewModel
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class UserDialogViewModel : BaseViewModel<UserDialogNavigator>() {

    fun onOkClick(){
        getNavigator()?.onOkClick()
    }

}