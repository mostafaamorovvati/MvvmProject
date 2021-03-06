package com.example.mvvmproject.ui.photos

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmproject.data.remote.model.Photo
import com.example.mvvmproject.databinding.PhotoItemLayoutBinding
import com.example.mvvmproject.ui.base.BaseViewHolder

class PhotoAdapter : RecyclerView.Adapter<BaseViewHolder>() {

    private var mPhotos: MutableList<Photo> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return PostViewHolder(
            PhotoItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) = holder.onBind(position)

    override fun getItemCount() = mPhotos.size


    @SuppressLint("NotifyDataSetChanged")
    fun setData(items: MutableList<Photo>) {
        mPhotos = items
        notifyDataSetChanged()
    }

    inner class PostViewHolder(private val mBinding: PhotoItemLayoutBinding) :
        BaseViewHolder(mBinding.root) {

        override fun onBind(position: Int) {
            val itemViewModel = PhotoAdapterViewModel(mPhotos[position])
            mBinding.photoItemViewModel = itemViewModel
            mBinding.executePendingBindings()
        }

    }
}