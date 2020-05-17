package com.covid19.presentation.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController


/**
 * Created by Amit Singh.
 * Tila
 * asingh@tila.com
 */

abstract class BaseFragment() : Fragment() {

    val navController by lazy {
        findNavController()
    }

    val fragmentActivity: AppCompatActivity? by lazy {
        if (activity is AppCompatActivity) {
            activity as AppCompatActivity
        } else {
            null
        }
    }

    open fun observeLiveData() {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        observeLiveData()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

}