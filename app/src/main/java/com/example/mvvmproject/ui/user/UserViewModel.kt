package com.example.mvvmproject.ui.user

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mvvmproject.R
import com.example.mvvmproject.utils.Resource
import com.example.mvvmproject.data.remote.model.User
import com.example.mvvmproject.data.repository.UserRepository
import com.example.mvvmproject.ui.base.BaseViewModel
import com.example.mvvmproject.utils.isNetworkConnected
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class UserViewModel(
    private val repository: UserRepository
) : BaseViewModel<UserNavigator>() {

    val mUsersList = MutableLiveData<Resource<List<User>>>()


    fun getUsers(context: Context) {
        viewModelScope.launch {
            mUsersList.postValue(Resource.loading(null))
            try {
                if (isNetworkConnected()) {
                    repository.getUsers().let {
                        if (it.isSuccessful) {
                            val users = it.body()
                            if (users != null) {
                                mUsersList.postValue(Resource.success(users))
                            } else mUsersList.postValue(
                                Resource.error(it.errorBody().toString(), null)
                            )
                        } else
                            mUsersList.postValue(Resource.error(it.errorBody().toString(), null))
                    }
                } else {
                    mUsersList.postValue(
                        Resource.error(
                            context.getString(R.string.no_internet_txt),
                            null
                        )
                    )
                }
            } catch (e: Exception) {
                mUsersList.postValue(Resource.error(e.message.toString(), null))
            }
        }
    }
}