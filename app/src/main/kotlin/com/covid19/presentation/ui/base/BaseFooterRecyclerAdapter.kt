package com.covid19.presentation.ui.base

import android.view.ViewGroup
import com.fptechscience.tila.common.extension.runOnUiThread


abstract class BaseFooterRecyclerAdapter<VH : androidx.recyclerview.widget.RecyclerView.ViewHolder, FH : androidx.recyclerview.widget.RecyclerView.ViewHolder, T> :
        BaseRecyclerAdapter<VH, T>() {

    var useFooter = true
        private set
    private val ITEM = 0
    private val LOADING = 1
    private var isLoadMore = true
    private var loadMore: (() -> Unit?)? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): androidx.recyclerview.widget.RecyclerView.ViewHolder {
        return if (viewType == LOADING) {
            onCreateFooterViewHolder(viewGroup, viewType)
        } else {
            onCreateItemViewHolder(viewGroup, viewType)
        }
    }

    override fun getItemCount(): Int {
        count = list.size
        if (useFooter) {
            count += 1
        }
        return count
    }

    override fun getItemViewType(position: Int): Int {
        if (position >= list.size && useFooter) {
            return LOADING
        }
        return ITEM
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: androidx.recyclerview.widget.RecyclerView.ViewHolder, position: Int) {
        if (position == getCount() && holder.itemViewType == LOADING) {
            onBindFooterViewHolder(holder as FH, position)
        } else {
            if (position == getCount() - 10 && isLoadMore) {
                runOnUiThread {
                    loadMore?.invoke()
                }
            }
            onBindItemViewHolder(holder as VH, position)
        }
    }

    fun showLoader() {
        useFooter = true
        notifyItemChanged(itemCount)
    }

    fun hideLoader() {
        useFooter = false
        notifyItemChanged(itemCount)
    }

    fun loadMore(f: () -> Unit) {
        loadMore = f
    }

    fun loadMoreCompleted() {
        isLoadMore = false
    }

    fun enableLoadMore() {
        isLoadMore = true
        useFooter = true
    }

    fun disableLoadMore() {
        isLoadMore = false
        useFooter = false
    }

    abstract fun onCreateFooterViewHolder(viewGroup: ViewGroup, viewType: Int): FH

    abstract fun onBindFooterViewHolder(holder: FH, position: Int)
}