package com.covid19.presentation.ui.splash

import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.covid19.databinding.ActivitySplashBinding
import com.covid19.presentation.ui.base.BaseAppCompatActivity
import com.covid19.presentation.ui.dashboard.DashBoardActivity
import com.fptechscience.tila.common.extension.startActivity


/**
 * Created by Amit Singh.
 * Tila
 * asingh@tila.com
 */

class SplashActivity : BaseAppCompatActivity() {

    private val binding by lazy {
        ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun layout() = binding.root

    private val _splashLiveData = MutableLiveData<Unit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler().postDelayed({
            _splashLiveData.postValue(Unit)
        }, 2000L)

    }

    override fun observeLiveData() {
        _splashLiveData.observe(this, Observer {
            startActivity<DashBoardActivity>()
            finish()
        })
    }

}