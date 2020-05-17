package com.covid19.presentation.ui.notification

import android.os.Bundle
import com.covid19.data.model.wrapped.EventObserver
import com.covid19.databinding.ActivityNotificationBinding
import com.covid19.presentation.ui.base.BaseAppCompatActivity
import com.covid19.presentation.ui.notification.adapter.NotificationAdapter
import com.covid19.presentation.vm.CovaIndiaAction
import com.covid19.presentation.vm.CovaIndiaVM
import com.fptechscience.tila.common.extension.toMutable
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * Created by Amit Singh.
 * Tila
 * asingh@tila.com
 */

class NotificationActivity : BaseAppCompatActivity() {

    private val binding by lazy {
        ActivityNotificationBinding.inflate(layoutInflater)
    }

    override fun layout() = binding.root

    private val covaIndiaVM: CovaIndiaVM by viewModel()

    private val adapter by lazy {
        NotificationAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        covaIndiaVM.onAction(CovaIndiaAction.Notification)
        binding.recyclerView.adapter = adapter
    }

    override fun observeLiveData() {
        covaIndiaVM.notificationLiveData.observe(this, EventObserver(this) {
            adapter.list = it.toMutable()
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}