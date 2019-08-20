package com.githubio.daeun1012.minigithub.view.util

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewPaginator(val recyclerView: RecyclerView,
                            val isLoading: () -> Boolean,
                            val loadMore: (Int) -> Unit,
                            val onLast: () -> Boolean = { true }): RecyclerView.OnScrollListener() {

    private val threshold = 10
    private var currentPage: Int = 0

    init {
        recyclerView.addOnScrollListener(this)
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val layoutManager = recyclerView.layoutManager
        val visibleItemCount = recyclerView.layoutManager?.childCount
        val totalItemCount = recyclerView.layoutManager?.itemCount
        val firstVisibleItemPosition = when (layoutManager) {
            is LinearLayoutManager -> layoutManager.findLastVisibleItemPosition()
            is GridLayoutManager -> layoutManager.findLastVisibleItemPosition()
            else -> return
        }

        if (onLast() || isLoading()) return

        if ((visibleItemCount!! + firstVisibleItemPosition + threshold) >= totalItemCount!!) {
            loadMore(++currentPage)
        }
    }

    fun resetCurrentPage() {
        this.currentPage = 0
    }
}