package com.paydaybank.android.features.home.welcome

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.EpoxyControllerAdapter
import com.paydaybank.android.R
import com.paydaybank.android.core.base.BaseFragment
import com.paydaybank.android.core.delegate.viewBinding
import com.paydaybank.android.core.extensions.asDp
import com.paydaybank.android.core.extensions.observe
import com.paydaybank.android.core.recyclerview.PagingController
import com.paydaybank.android.databinding.FragmentWelcomeBinding
import com.paydaybank.android.features.home.HomeState
import com.paydaybank.android.features.home.HomeViewModel
import com.paydaybank.android.models.*
import com.paydaybank.data.model.TransactionEntity
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.insetter.applySystemWindowInsetsToPadding
import timber.log.Timber

@AndroidEntryPoint
class WelcomeFragment: BaseFragment(R.layout.fragment_welcome) {

    private val viewModel: HomeViewModel by viewModels(ownerProducer = { requireActivity() })

    override val binding by viewBinding(FragmentWelcomeBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Observe state
        observe(viewModel.state, ::refreshViewStates)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inset views
        binding.toolbar.applySystemWindowInsetsToPadding(top = true)
        binding.recyclerView.applySystemWindowInsetsToPadding(bottom = true)

        // Bind controller
        val controller = PagingController<TransactionEntity>(
            { _, item ->
                ModelViewTransactionModel_()
                    .id(item?.id)
                    .transaction(item)
            },
            {
                val homeState = viewModel.state.value ?: return@PagingController
                modelViewMonthControl {
                    id("month", "control")
                    months(homeState.months)
                    selectedMonth(homeState.selectedMonth)
                    onSelectMonth(viewModel::changeMonth)
                }
                modelViewChart {
                    id("chart", "bar")
                    sums(homeState.filteredSums)
                }
                homeState.filteredSums.forEach {
                    modelViewCategory {
                        id("category", it.title)
                        categorizedSum(it)
                    }
                }
                modelViewPieChart {
                    id("chart", "pie")
                    sums(homeState.filteredSums)
                }
                insertItemModels(it)
            }
        )
        binding.recyclerView.setControllerAndBuildModels(controller)

        // Add item decaoration
        binding.recyclerView.addItemDecoration(itemDecoration)

        // Observe paginated transactions
        observe(viewModel.paginatedTransactions, controller::submitList)
    }

    override fun onDestroyView() {
        binding.recyclerView.removeItemDecoration(itemDecoration)
        super.onDestroyView()
    }

    private fun refreshViewStates(homeState: HomeState?) {
        binding.recyclerView.requestModelBuild()
    }


    private val itemDecoration = object: RecyclerView.ItemDecoration(){

        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            try{
                val position = parent.getChildAdapterPosition(view)
                val adapter = parent.adapter
                if(adapter is EpoxyControllerAdapter){
                    val model = adapter.getModelAtPosition(position)
                    when(model::class){
                        ModelViewChartModel_::class -> outRect.set(20.asDp,20.asDp,20.asDp,10.asDp)
                        ModelViewPieChartModel_::class -> outRect.set(20.asDp,10.asDp,20.asDp,10.asDp)
                        ModelViewTransactionModel_::class -> outRect.set(20.asDp,0,20.asDp,0)
                        ModelViewCategoryModel_::class -> outRect.set(20.asDp,0,20.asDp,0)
                        else -> outRect.setEmpty()
                    }
                }
            }catch (ex:Exception){
                Timber.w(ex)
            }
        }
    }


}