package com.githubio.daeun1012.minigithub.view.main.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.githubio.daeun1012.minigithub.AppExecutors
import com.githubio.daeun1012.minigithub.R
import com.githubio.daeun1012.minigithub.data.remote.models.User
import com.githubio.daeun1012.minigithub.databinding.ItemSearchBinding
import com.githubio.daeun1012.minigithub.view.util.DataBoundListAdapter

class SearchListAdapter(appExecutors: AppExecutors) : DataBoundListAdapter<User, ItemSearchBinding>(
        appExecutors = appExecutors,
        diffCallback = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id && oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.score == oldItem.score
            }
        }
) {

    override fun createBinding(parent: ViewGroup): ItemSearchBinding {
        return DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_search,
                parent,
                false
        )
    }

    override fun bind(binding: ItemSearchBinding, item: User) {
//        binding.ivAvatar = item.avatar_url
        binding.tvName.text = item.name
        binding.tvScore.text = item.score.toString()
    }



}