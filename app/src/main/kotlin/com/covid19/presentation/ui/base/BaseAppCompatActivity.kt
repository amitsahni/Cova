package com.covid19.presentation.ui.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.covid19.databinding.ActivityBaseBinding
import com.fptechscience.tila.common.extension.castAs
import com.fptechscience.tila.common.extension.gone
import com.fptechscience.tila.common.extension.snackBar
import com.fptechscience.tila.common.extension.visible


/**
 * Created by Amit Singh.
 * Tila
 * asingh@tila.com
 */

abstract class BaseAppCompatActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityBaseBinding.inflate(layoutInflater).apply {
            containerView.addView(layout())
        }
    }
    val baseParentView by lazy {
        binding.baseParentView
    }

    /***
     *  Add UI View
     */
    abstract fun layout(): View

    /**
     *  Observe LiveData/Flow
     */
    open fun observeLiveData() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        observeLiveData()
    }

    fun showLoader() {
        binding.animationView.visible()
    }

    fun hideLoader() {
        binding.animationView.gone()
    }
}

fun AppCompatActivity.showLoader() {
    castAs<BaseAppCompatActivity> {
        showLoader()
    }
}

fun AppCompatActivity.hideLoader() {
    castAs<BaseAppCompatActivity> {
        hideLoader()
    }
}

fun AppCompatActivity.snackBar(msg: String) {
    castAs<BaseAppCompatActivity> {
        baseParentView.snackBar(msg)
    }
}