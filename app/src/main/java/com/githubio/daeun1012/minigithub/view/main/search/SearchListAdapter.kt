package com.githubio.daeun1012.minigithub.view.main.search

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.githubio.daeun1012.minigithub.AppExecutors
import com.githubio.daeun1012.minigithub.R
import com.githubio.daeun1012.minigithub.data.remote.models.User
import com.githubio.daeun1012.minigithub.databinding.ItemSearchBinding
import com.githubio.daeun1012.minigithub.view.util.DataBoundListAdapter

class SearchListAdapter(
        private val dataBindingComponent: DataBindingComponent,
        appExecutors: AppExecutors
) : DataBoundListAdapter<User, ItemSearchBinding>(
        appExecutors = appExecutors,
        diffCallback = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id && oldItem.login == newItem.login
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
                false,
                dataBindingComponent
        )
    }

    override fun bind(binding: ItemSearchBinding, item: User) {
        binding.user = item
        binding.tvName.text = item.login
        binding.tvScore.text = item.score.toString()
    }


}