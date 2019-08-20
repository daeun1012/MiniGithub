package com.githubio.daeun1012.minigithub.view.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.githubio.daeun1012.minigithub.view.main.like.LikeFragment
import com.githubio.daeun1012.minigithub.view.main.search.SearchFragment

class MainPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return if (position == 0) {
            SearchFragment.newInstance()
        } else {
            LikeFragment.newInstance()
        }
    }

    override fun getCount(): Int {
        // Show 3 total pages.
        return 2
    }
}