package com.covid19.presentation.ui.world

import android.os.Bundle
import com.covid19.data.model.request.global.CountrySearchRequest
import com.covid19.data.model.wrapped.EventObserver
import com.covid19.databinding.ActivityWorldWideBinding
import com.covid19.presentation.ui.base.BaseAppCompatActivity
import com.covid19.presentation.ui.world.adapter.WorldWideAdapter
import com.covid19.presentation.vm.CovaGlobalAction
import com.covid19.presentation.vm.CovaGlobalVM
import com.fptechscience.tila.common.extension.toMutable
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * Created by Amit Singh.
 * Tila
 * asingh@tila.com
 */

class WorldWideActivity : BaseAppCompatActivity() {

    private val binding by lazy {
        ActivityWorldWideBinding.inflate(layoutInflater)
    }
    private val adapter by lazy {
        val adapter = WorldWideAdapter()
        adapter.hideLoader()
        adapter
    }
    private var page: Int = 1
    private val covaGlobalVM: CovaGlobalVM by viewModel()

    override fun layout() = binding.root

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.recyclerView.adapter = adapter
        covaGlobalVM.onAction(CovaGlobalAction.CountrySearch())
        adapter.loadMore {
            val request = CountrySearchRequest(page = page.toString())
            covaGlobalVM.onAction(CovaGlobalAction.CountrySearch(request))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun observeLiveData() {
        covaGlobalVM.countrySearchLiveData.observe(this, EventObserver(this) {
            adapter.list.addAll(it.countrySearch.rows.toMutable())
            val count = adapter.list.size
            it.countrySearch.paginationMeta?.let {
                val c = it.currentPage ?: 0
                val t = it.totalPages ?: 0
                if (c != t) {
                    page = c + 1
                }
            }
            if (count == it.countrySearch.paginationMeta?.totalRecords) {
                adapter.loadMoreCompleted()
            } else {
                adapter.enableLoadMore()
                adapter.showLoader()
            }
            adapter.notifyDataSetChanged()
        })
    }

}