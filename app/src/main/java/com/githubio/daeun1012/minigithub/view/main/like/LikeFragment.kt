package com.githubio.daeun1012.minigithub.view.main.like

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.githubio.daeun1012.minigithub.AppExecutors
import com.githubio.daeun1012.minigithub.R
import com.githubio.daeun1012.minigithub.databinding.FragmentLikeBinding
import com.githubio.daeun1012.minigithub.di.ViewModelFactory
import com.githubio.daeun1012.minigithub.view.main.MainActivity
import com.githubio.daeun1012.minigithub.view.util.FragmentDataBindingComponent
import com.githubio.daeun1012.minigithub.view.util.autoCleared
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

    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)


    private var binding by autoCleared<FragmentLikeBinding>()

    private var adapter by autoCleared<LikeListAdapter>()

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_like, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        initRecyclerView()
        val rvAdapter = LikeListAdapter(
                dataBindingComponent = dataBindingComponent,
                appExecutors = appExecutors
        ) { user ->
            // save user
            viewModel.likeUser(user)
            if( activity is MainActivity) {
                (activity as MainActivity).refreshSearchFragment()
            }

        }

        binding.userList.adapter = rvAdapter
        adapter = rvAdapter
    }

    private fun initRecyclerView() {
        binding.results = viewModel.results
        viewModel.results.observe(viewLifecycleOwner, Observer { result ->
            adapter.submitList(result)
        })
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