package com.githubio.daeun1012.minigithub.view.main.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.githubio.daeun1012.minigithub.AppExecutors
import com.githubio.daeun1012.minigithub.R
import com.githubio.daeun1012.minigithub.data.remote.models.User
import com.githubio.daeun1012.minigithub.databinding.ItemUserBinding
import com.githubio.daeun1012.minigithub.view.util.DataBoundListAdapter

class SearchListAdapter(
        private val dataBindingComponent: DataBindingComponent,
        appExecutors: AppExecutors,
        private val userClickCallback: ((User) -> Unit)?
) : DataBoundListAdapter<User, ItemUserBinding>(
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

    override fun createBinding(parent: ViewGroup): ItemUserBinding {
        val binding = DataBindingUtil.inflate<ItemUserBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_user,
                parent,
                false,
                dataBindingComponent
        )
        binding.root.setOnClickListener {
            binding.user?.let {
                it.isLike = !it.isLike
                userClickCallback?.invoke(it)
                notifyDataSetChanged()
            }
        }
        return binding
    }

    override fun bind(binding: ItemUserBinding, item: User, position: Int) {
        binding.user = item
        binding.tvName.text = item.login
        binding.tvScore.text = item.score.toString()
    }


}