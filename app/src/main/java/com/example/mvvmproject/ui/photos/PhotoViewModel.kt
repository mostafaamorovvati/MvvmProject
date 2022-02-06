package com.example.mvvmproject.ui.photos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mvvmproject.data.remote.model.Photo
import com.example.mvvmproject.data.remote.model.Resource
import com.example.mvvmproject.data.repository.PhotoRepository
import com.example.mvvmproject.ui.base.BaseViewModel
import com.example.mvvmproject.utils.NetworkHelper
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class PhotoViewModel(
    private val repository: PhotoRepository,
    private val mNetworkHelper: NetworkHelper
) : BaseViewModel<PhotoNavigator>() {

    val mPhotoList = MutableLiveData<Resource<List<Photo>>>()

    init {
        getPhotos()
    }

    private fun getPhotos() {
        viewModelScope.launch {
            mPhotoList.postValue(Resource.loading(null))
            try {
                if (mNetworkHelper.isNetworkConnected()) {
                    repository.getPhotos().let {
                        if (it.isSuccessful) {
                            val photos = it.body()
                            if (photos != null) {
                                mPhotoList.postValue(Resource.success(photos))
                            } else mPhotoList.postValue(
                                Resource.error(it.errorBody().toString(), null)
                            )
                        } else
                            mPhotoList.postValue(Resource.error(it.errorBody().toString(), null))
                    }
                } else
                    mPhotoList.postValue(Resource.error("No internet connection", null))
            } catch (e: Exception) {
                mPhotoList.postValue(Resource.error(e.message.toString(), null))
            }
        }
    }

}