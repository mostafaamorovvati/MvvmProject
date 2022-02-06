package com.example.mvvmproject.ui.user

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmproject.data.remote.model.User
import com.example.mvvmproject.databinding.UserItemLayoutBinding
import com.example.mvvmproject.ui.base.BaseViewHolder

class UserAdapter : RecyclerView.Adapter<BaseViewHolder>() {

    private var mUsers: MutableList<User> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return UserViewHolder(
            UserItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) = holder.onBind(position)

    override fun getItemCount() = mUsers.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(items: MutableList<User>) {
        mUsers = items
        notifyDataSetChanged()
    }

    inner class UserViewHolder(private val mBinding: UserItemLayoutBinding) :
        BaseViewHolder(mBinding.root) {

        override fun onBind(position: Int) {
            val itemViewModel = UserItemViewModel(mUsers[position])
            mBinding.userItemViewModel = itemViewModel
            mBinding.executePendingBindings()
        }
    }
}