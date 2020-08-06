package com.paydaybank.android.features.home.welcome

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.paydaybank.android.R
import com.paydaybank.android.core.base.BaseFragment
import com.paydaybank.android.core.delegate.viewBinding
import com.paydaybank.android.core.extensions.observe
import com.paydaybank.android.databinding.FragmentWelcomeBinding
import com.paydaybank.android.features.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class WelcomeFragment: BaseFragment(R.layout.fragment_welcome) {

    private val viewModel: HomeViewModel by viewModels(ownerProducer = { requireActivity() })

    override val binding by viewBinding(FragmentWelcomeBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Observe paginated transactions
        observe(viewModel.paginatedTransactions){
            Timber.d("${it?.size}")
        }
    }


}