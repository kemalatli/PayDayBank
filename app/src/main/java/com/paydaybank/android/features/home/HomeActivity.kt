package com.paydaybank.android.features.home

import com.paydaybank.android.R
import com.paydaybank.android.core.base.BaseNavigationActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity: BaseNavigationActivity() {

    override val graphId: Int get() = R.navigation.navigation_home

}