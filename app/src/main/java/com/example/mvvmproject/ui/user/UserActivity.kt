package com.example.mvvmproject.ui.user

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmproject.BR
import com.example.mvvmproject.R
import com.example.mvvmproject.data.remote.model.User
import com.example.mvvmproject.utils.Status
import com.example.mvvmproject.databinding.ActivityUserBinding
import com.example.mvvmproject.ui.base.BaseActivity
import com.example.mvvmproject.ui.user.userDialog.UserDialog
import com.example.mvvmproject.utils.gone
import com.example.mvvmproject.utils.toast
import com.example.mvvmproject.utils.visible
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class UserActivity : BaseActivity<ActivityUserBinding, UserViewModel>(),
    UserNavigator,
    UserItemViewModel.OnItemClickListener {

    private val mUserAdapter: UserAdapter by inject()
    private val mViewModel: UserViewModel by viewModel()
    private lateinit var mBinding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = getViewDataBinding()
        mViewModel.setNavigator(this)

        mViewModel.getUsers(this)

        setupUsersObserver()
        setupUserList()

    }


    private fun setupUsersObserver() {
        mViewModel.mUsersList.observe(this, {
            it?.let {
                when (it.status) {
                    Status.SUCCESS -> {
                        mBinding.progressBar.gone()
                        mBinding.userList.visible()
                        mUserAdapter.setData(it.data?.toMutableList() ?: mutableListOf())
                    }
                    Status.ERROR -> {
                        mBinding.progressBar.gone()
                        mBinding.userList.gone()
                        this@UserActivity.toast(it.message.toString())
                    }
                    Status.LOADING -> {
                        mBinding.progressBar.visible()
                        mBinding.userList.gone()
                    }
                }
            }
        })
    }

    private fun setupUserList() {
        mBinding.userList.apply {
            mUserAdapter.mListener = this@UserActivity
            adapter = mUserAdapter
            layoutManager = LinearLayoutManager(this@UserActivity)
        }
    }

    override fun getBindingVariable() = BR.UserViewModel

    override fun getLayoutId() = R.layout.activity_user

    override fun getViewModel() = mViewModel

    companion object {
        fun openActivity(activity: FragmentActivity): Intent {
            return Intent(activity, UserActivity::class.java)
        }
    }

    override fun onItemClick(item: User) {
        UserDialog(
            "NAME: ${item.name}",
            "USERNAME: ${item.username}",
            "EMAIL: ${item.email}",
            "ADDRESS: ${item.address?.city} / ${item.address?.street} / ${item.address?.suite}",
            "WEBSITE: ${item.website}",
            "PHONE: ${item.phone}",
            "COMPANY: ${item.company?.name}"
        ).show(supportFragmentManager, "")
    }

}