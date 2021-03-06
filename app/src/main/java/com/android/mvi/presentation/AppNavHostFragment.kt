package com.android.mvi.presentation

import android.content.Context
import androidx.navigation.fragment.NavHostFragment
import com.android.mvi.presentation.main.MainFragmentFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AppNavHostFragment: NavHostFragment() {

    @Inject
    lateinit var fragmentFactory: MainFragmentFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        childFragmentManager.fragmentFactory = fragmentFactory
    }
}