package com.example.mvvmproject.ui.main

import android.os.Bundle
import com.example.mvvmproject.BR
import com.example.mvvmproject.R
import com.example.mvvmproject.databinding.ActivityMainBinding
import com.example.mvvmproject.ui.base.BaseActivity
import com.example.mvvmproject.ui.photos.PhotoActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), MainNavigator {

    private lateinit var mBinding: ActivityMainBinding
    private val mViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel.setNavigator(this)
        mBinding = getViewDataBinding()
    }

    override fun getBindingVariable() = BR.MainViewModel

    override fun getLayoutId() = R.layout.activity_main

    override fun getViewModel() = mViewModel

    override fun openPhotoActivity() {
        startActivity(PhotoActivity.openActivity(this))
    }
}