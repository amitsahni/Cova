package com.covid19.presentation.ui.dashboard

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.navigation.fragment.findNavController
import com.covid19.R
import com.covid19.databinding.ActivityDashboardBinding
import com.covid19.presentation.ui.base.BaseAppCompatActivity
import com.covid19.presentation.ui.world.WorldWideActivity
import com.covid19.presentation.vm.DashBoardVM
import com.fptechscience.tila.common.extension.startActivity
import kotlinx.android.synthetic.main.activity_dashboard.*
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * Created by Amit Singh.
 * Tila
 * asingh@tila.com
 */

class DashBoardActivity : BaseAppCompatActivity() {

    private val binding by lazy {
        ActivityDashboardBinding.inflate(layoutInflater)
    }

    private val drawerToggle by lazy {
        ActionBarDrawerToggle(this, binding.drawerLayout, R.string.app_name, R.string.app_name)
    }

    private val navController by lazy {
        navigationController.findNavController()
    }

    private val dashBoardVM: DashBoardVM by viewModel()

    override fun layout() = binding.root

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
        binding.navView.setNavigationItemSelectedListener {
            binding.drawerLayout.closeDrawers()
            when (it.itemId) {
                R.id.drawerWorldWide -> {
                    startActivity<WorldWideActivity>()
                }

                R.id.drawerZones -> {

                }
            }
            true
        }
    }

    /*** Hamburger icon click event ***/
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                drawerToggle.onOptionsItemSelected(item)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}