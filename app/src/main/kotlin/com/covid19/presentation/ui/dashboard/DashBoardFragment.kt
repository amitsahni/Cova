package com.covid19.presentation.ui.dashboard

import android.os.Bundle
import android.view.*
import com.covid19.R
import com.covid19.data.model.wrapped.EventObserver
import com.covid19.databinding.FragDashboardBinding
import com.covid19.presentation.ui.base.BaseFragment
import com.covid19.presentation.ui.notification.NotificationActivity
import com.covid19.presentation.vm.CovaGlobalAction
import com.covid19.presentation.vm.CovaGlobalVM
import com.fptechscience.tila.common.extension.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * Created by Amit Singh.
 * Tila
 * asingh@tila.com
 */

class DashBoardFragment : BaseFragment() {
    private var _binding: FragDashboardBinding? = null
    private val binding get() = _binding!!

    private val covaGlobalVM: CovaGlobalVM by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = _binding ?: FragDashboardBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return _binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        covaGlobalVM.onAction(CovaGlobalAction.GeneralStat)
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            covaGlobalVM.onAction(CovaGlobalAction.GeneralStat)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_notification, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuNotification -> {
                startActivity<NotificationActivity>()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun observeLiveData() {
        covaGlobalVM.generalStatLiveData.observe(
            viewLifecycleOwner,
            EventObserver(fragmentActivity) {
                it.generalStat.apply {
                    binding.updateAt.text = "Last Updated At ${this.lastUpdate}"
                    binding.confirmed.text = this.totalCases
                    binding.recovered.text = this.recoveryCases
                    binding.active.text = this.currentlyInfected
                    binding.death.text = this.deathCases
                }
            })

        covaGlobalVM.countrySearchLiveData.observe(
            viewLifecycleOwner,
            EventObserver(fragmentActivity) {

            })
    }
}