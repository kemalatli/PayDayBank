package com.paydaybank.android.features.auth.create

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.paydaybank.android.R
import com.paydaybank.android.core.base.BaseFragment
import com.paydaybank.android.core.delegate.viewBinding
import com.paydaybank.android.core.extensions.observe
import com.paydaybank.android.databinding.FragmentCreateAccountBinding
import com.paydaybank.android.features.auth.AuthViewModel
import com.paydaybank.data.repository.user.model.UserState
import dev.chrisbanes.insetter.applySystemWindowInsetsToPadding

class CreateAccountFragment:BaseFragment(R.layout.fragment_create_account) {

    private val viewModel: AuthViewModel by viewModels(ownerProducer = { requireActivity() })

    override val binding by viewBinding(FragmentCreateAccountBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Observe the user state
        observe(viewModel.userState){
            binding.submit.isEnabled = it !is UserState.Loading
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Inset views
        binding.root.applySystemWindowInsetsToPadding(bottom = true)
        // Submit
        binding.submit.setOnClickListener {
            viewModel.createAccount(
                binding.firstName.text,
                binding.lastName.text,
                binding.phoneNumber.text,
                binding.email.text,
                binding.password.text,
                if(binding.genderholder.checkedButtonId == R.id.male) "male" else "female"
            )
        }
    }

}