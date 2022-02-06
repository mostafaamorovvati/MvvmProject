package com.example.mvvmproject.ui.user

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mvvmproject.R
import com.example.mvvmproject.data.remote.model.Resource
import com.example.mvvmproject.data.remote.model.User
import com.example.mvvmproject.data.repository.UserRepository
import com.example.mvvmproject.ui.base.BaseViewModel
import com.example.mvvmproject.utils.NetworkHelper
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class UserViewModel(
    private val repository: UserRepository,
    private val mNetworkHelper: NetworkHelper
) : BaseViewModel<UserNavigator>() {

    val mUsersList = MutableLiveData<Resource<List<User>>>()


    fun getUsers(context: Context) {
        viewModelScope.launch {
            mUsersList.postValue(Resource.loading(null))
            try {
                if (mNetworkHelper.isNetworkConnected()) {
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
                            context.getString(R.string.no_interner_txt),
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