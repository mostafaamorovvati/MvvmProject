package com.example.mvvmproject.ui.photos

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mvvmproject.R
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

    val mPhotosList = MutableLiveData<Resource<List<Photo>>>()

    fun getPhotos(context: Context) {
        viewModelScope.launch {
            mPhotosList.postValue(Resource.loading(null))
            try {
                if (mNetworkHelper.isNetworkConnected()) {
                    repository.getPhotos().let {
                        if (it.isSuccessful) {
                            val photos = it.body()
                            if (photos != null) {
                                mPhotosList.postValue(Resource.success(photos))
                            } else mPhotosList.postValue(
                                Resource.error(it.errorBody().toString(), null)
                            )
                        } else
                            mPhotosList.postValue(Resource.error(it.errorBody().toString(), null))
                    }
                } else
                    mPhotosList.postValue(
                        Resource.error(
                            context.getString(R.string.no_interner_txt),
                            null
                        )
                    )
            } catch (e: Exception) {
                mPhotosList.postValue(Resource.error(e.message.toString(), null))
            }
        }
    }

}