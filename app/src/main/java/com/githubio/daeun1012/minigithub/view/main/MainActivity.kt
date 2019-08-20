package com.githubio.daeun1012.minigithub.view.main

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.githubio.daeun1012.minigithub.R
import com.githubio.daeun1012.minigithub.databinding.ActivityMainBinding
import com.githubio.daeun1012.minigithub.di.ViewModelFactory
import com.githubio.daeun1012.minigithub.view.util.vm
import com.google.android.material.tabs.TabLayout
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity

import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(){

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by lazy { vm(viewModelFactory, MainViewModel::class) }
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var mMainPagerAdapter: MainPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

//        setSupportActionBar(toolbar)
//        mMainPagerAdapter = MainPagerAdapter(supportFragmentManager)

        binding.container.adapter = mMainPagerAdapter

        binding.container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tabs))
        binding.tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(binding.container))

    }
}
