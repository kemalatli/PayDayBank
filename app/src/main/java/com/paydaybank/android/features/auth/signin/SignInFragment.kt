package com.paydaybank.android.features.auth.signin

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.paydaybank.android.R
import com.paydaybank.android.core.base.BaseFragment
import com.paydaybank.android.core.delegate.viewBinding
import com.paydaybank.android.core.extensions.observe
import com.paydaybank.android.databinding.FragmentSigninBinding
import com.paydaybank.android.features.auth.AuthViewModel
import com.paydaybank.android.features.home.HomeActivity
import com.paydaybank.data.repository.user.model.UserState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment: BaseFragment(R.layout.fragment_signin) {

    private val viewModel: AuthViewModel by viewModels(ownerProducer = { requireActivity() })

    override val binding by viewBinding(FragmentSigninBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Observe the user state
        observe(viewModel.userState){
            binding.submit.isEnabled = it !is UserState.Loading
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Submit
        binding.submit.setOnClickListener {
            viewModel.signIn(
                binding.email.text,
                binding.password.text
            )
        }
    }


}