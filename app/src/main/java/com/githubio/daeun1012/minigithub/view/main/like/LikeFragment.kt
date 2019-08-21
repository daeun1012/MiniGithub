package com.githubio.daeun1012.minigithub.view.main.like

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.githubio.daeun1012.minigithub.AppExecutors
import com.githubio.daeun1012.minigithub.R
import com.githubio.daeun1012.minigithub.databinding.FragmentLikeBinding
import com.githubio.daeun1012.minigithub.di.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class LikeFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var appExecutors: AppExecutors

    private val viewModel: LikeViewModel by viewModels {
        viewModelFactory
    }
    private lateinit var binding: FragmentLikeBinding

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_like, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    companion object {

        fun newInstance(): LikeFragment {
            val fragment = LikeFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}