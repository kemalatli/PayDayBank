package com.paydaybank.android.features.home.welcome

import android.os.Bundle
import android.view.View
import androidx.core.view.marginStart
import androidx.fragment.app.viewModels
import com.paydaybank.android.R
import com.paydaybank.android.core.base.BaseFragment
import com.paydaybank.android.core.delegate.viewBinding
import com.paydaybank.android.core.extensions.observe
import com.paydaybank.android.databinding.FragmentWelcomeBinding
import com.paydaybank.android.features.home.HomeState
import com.paydaybank.android.features.home.HomeViewModel
import com.paydaybank.android.models.modelViewChart
import com.paydaybank.android.models.modelViewPieChart
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.insetter.Insetter
import dev.chrisbanes.insetter.applySystemWindowInsetsToPadding
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
        // Observe state
        observe(viewModel.state, ::refreshViewStates)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Inset views
        binding.toolbar.applySystemWindowInsetsToPadding(top = true)
    }

    private fun refreshViewStates(homeState: HomeState?) {
        Timber.d("Welcome screen state $homeState")
        if(homeState==null) return
        binding.recyclerView.withModels {
            modelViewChart {
                id("chart", "bar")
                sums(homeState.filteredSums)
            }
            modelViewPieChart {
                id("chart", "pie")
                sums(homeState.filteredSums)
            }
        }
    }


}