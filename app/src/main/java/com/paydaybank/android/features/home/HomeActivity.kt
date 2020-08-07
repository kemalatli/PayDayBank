package com.paydaybank.android.features.home

import android.os.Bundle
import androidx.activity.viewModels
import com.paydaybank.android.R
import com.paydaybank.android.core.base.BaseNavigationActivity
import com.paydaybank.android.core.extensions.observe
import com.paydaybank.android.launcher.LauncherActivity
import com.paydaybank.data.repository.user.model.UserState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity: BaseNavigationActivity() {

    override val graphId: Int get() = R.navigation.navigation_home

    private val viewModel: HomeViewModel by viewModels{ defaultViewModelProviderFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Warning messages
        listenToWarningMessages(viewModel.warningMessage)
        // Observe user state for logout
        observe(viewModel.userState){
            if(it !is UserState.Authenticated){
                navigateToActivity(LauncherActivity::class.java)
            }
        }
    }

}