package com.paydaybank.android.core.base

import android.os.Bundle
import androidx.annotation.NavigationRes
import androidx.navigation.fragment.NavHostFragment
import com.paydaybank.android.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class BaseNavigationActivity: BaseActivity() {

    @NavigationRes open val graphId:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_navigation)
        // Navigation setup
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        navHostFragment.navController.setGraph(graphId)
    }

}