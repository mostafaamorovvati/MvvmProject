package com.example.mvvmproject.ui.user.userDialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.mvvmproject.R
import com.example.mvvmproject.databinding.UserDialogBinding
import com.example.mvvmproject.ui.base.BaseDialog
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class UserDialog(
    private val name: String,
    private val userName: String,
    private val email: String,
    private val address: String,
    private val website: String,
    private val phone: String,
    private val company: String
) : BaseDialog<Any>(), UserDialogNavigator {

    private lateinit var mBinding: UserDialogBinding
    private val mViewModel: UserDialogViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.user_dialog,
            container,
            false
        )
        mBinding.userDialogViewModel = mViewModel
        mViewModel.setNavigator(this)


        mBinding.apply {
            tvName.text = name
            tvUserName.text = userName
            tvEmail.text = email
            tvAddress.text = address
            tvWebsite.text = website
            tvPhone.text = phone
            tvCompany.text = company
        }

        return mBinding.root
    }

    override fun onOkClick() {
        dismissAllowingStateLoss()
    }

}