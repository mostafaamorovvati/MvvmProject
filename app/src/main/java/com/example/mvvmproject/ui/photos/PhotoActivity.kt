package com.example.mvvmproject.ui.photos

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmproject.BR
import com.example.mvvmproject.R
import com.example.mvvmproject.data.remote.model.Status
import com.example.mvvmproject.databinding.ActivityPhotoBinding
import com.example.mvvmproject.ui.base.BaseActivity
import com.example.mvvmproject.utils.gone
import com.example.mvvmproject.utils.toast
import com.example.mvvmproject.utils.visible
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class PhotoActivity : BaseActivity<ActivityPhotoBinding, PhotoViewModel>(), PhotoNavigator {

    private val mPhotoAdapter: PhotoAdapter by inject()
    private val mViewModel: PhotoViewModel by viewModel()
    private lateinit var mBinding: ActivityPhotoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel.setNavigator(this)
        mBinding = getViewDataBinding()

        mViewModel.getPhotos(this)

        setupPhotosObserver()
        setupPhotoList()

    }

    private fun setupPhotosObserver() {
        mViewModel.mPhotosList.observe(this, {
            it?.let {
                when (it.status) {
                    Status.SUCCESS -> {
                        mBinding.progressBar.gone()
                        mBinding.photoList.visible()
                        mPhotoAdapter.setData(it.data?.toMutableList() ?: mutableListOf())
                    }
                    Status.ERROR -> {
                        mBinding.progressBar.gone()
                        mBinding.photoList.gone()
                        this@PhotoActivity.toast(it.message.toString())
                    }
                    Status.LOADING -> {
                        mBinding.progressBar.visible()
                        mBinding.photoList.gone()
                    }
                }
            }
        })
    }

    private fun setupPhotoList() {
        mBinding.photoList.apply {
            adapter = mPhotoAdapter
            layoutManager = LinearLayoutManager(this@PhotoActivity)
        }
    }

    override fun getBindingVariable() = BR.PhotoViewModel

    override fun getLayoutId() = R.layout.activity_photo

    override fun getViewModel() = mViewModel

    companion object {
        fun openActivity(activity: FragmentActivity): Intent {
            return Intent(activity, PhotoActivity::class.java)
        }
    }
}