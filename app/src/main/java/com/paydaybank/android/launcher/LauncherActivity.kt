package com.paydaybank.android.launcher


import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.paydaybank.android.core.base.BaseActivity
import com.paydaybank.android.core.extensions.observe
import com.paydaybank.android.features.auth.AuthActivity
import com.paydaybank.android.features.home.HomeActivity
import com.paydaybank.data.repository.user.model.UserState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LauncherActivity: BaseActivity() {

    private val viewModel:LauncherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Observe the user state for initial destination
        observe(viewModel.userState){
            when(it){
                is UserState.Unauthenticated -> navigateToEntryPoint(AuthActivity::class.java)
                is UserState.Authenticated -> navigateToEntryPoint(HomeActivity::class.java)
            }
        }

    }

    /**
     * Navigate to entry point of the app
     */
    private fun navigateToEntryPoint(clazz: Class<*>) {
        val intent = Intent(this, clazz)
        startActivity(intent)
        finish()
    }

}