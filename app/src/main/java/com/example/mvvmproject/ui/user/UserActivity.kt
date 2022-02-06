package com.example.mvvmproject.ui.user

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.mvvmproject.BR
import com.example.mvvmproject.R
import com.example.mvvmproject.databinding.ActivityUserBinding
import com.example.mvvmproject.ui.base.BaseActivity
import com.example.mvvmproject.ui.photos.PhotoActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class UserActivity : BaseActivity<ActivityUserBinding, UserViewModel>(), UserNavigator {

    private lateinit var mBinding: ActivityUserBinding
    private val mViewModel: UserViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = getViewDataBinding()
        mViewModel.setNavigator(this)
    }

    override fun getBindingVariable() = BR.UserViewModel

    override fun getLayoutId() = R.layout.activity_user

    override fun getViewModel() = mViewModel

    companion object {
        fun openActivity(activity: FragmentActivity): Intent {
            return Intent(activity, UserActivity::class.java)
        }
    }

}