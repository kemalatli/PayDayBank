package com.paydaybank.android.launcher


import android.os.Bundle
import androidx.activity.viewModels
import com.paydaybank.android.core.base.BaseActivity
import com.paydaybank.android.core.extensions.observe
import com.paydaybank.android.features.auth.AuthActivity
import com.paydaybank.android.features.home.HomeActivity
import com.paydaybank.data.repository.user.model.UserState
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class LauncherActivity: BaseActivity() {

    private val viewModel:LauncherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Observe the user state for initial destination
        observe(viewModel.userState){
            Timber.d("$it")
            when(it){
                is UserState.Unauthenticated -> navigateToActivity(AuthActivity::class.java)
                is UserState.Authenticated -> navigateToActivity(HomeActivity::class.java)
            }
        }

    }

}