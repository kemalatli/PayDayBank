package com.paydaybank.android.features.auth

import android.os.Bundle
import androidx.activity.viewModels
import com.paydaybank.android.R
import com.paydaybank.android.core.base.BaseNavigationActivity
import com.paydaybank.android.core.extensions.observe
import com.paydaybank.android.features.home.HomeActivity
import com.paydaybank.data.repository.user.model.UserState
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class AuthActivity: BaseNavigationActivity() {

    private val viewModel: AuthViewModel by viewModels{ defaultViewModelProviderFactory }

    override val graphId: Int get() = R.navigation.navigation_auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Listen to warnings
        listenToWarningMessages(viewModel.warningMessage)
        // Observe the user state
        observe(viewModel.userState){
            when(it){
                is UserState.Authenticated -> navigateToActivity(HomeActivity::class.java)
                is UserState.FailedSignIn -> viewModel.warn(it.message)
            }
        }
    }

}