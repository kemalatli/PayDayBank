package com.paydaybank.android.features.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import com.paydaybank.android.core.base.BaseViewModel
import com.paydaybank.data.repository.transaction.TransactionRepository
import com.paydaybank.data.repository.user.UserRepository
import kotlinx.coroutines.flow.collect


class HomeViewModel  @ViewModelInject constructor(
    private val userRepository: UserRepository,
    private val transactionRepository: TransactionRepository
): BaseViewModel() {

    // Home state
    private val _state:MutableLiveData<HomeState> = MutableLiveData()
    val state: LiveData<HomeState> = _state


    val paginatedTransactions = LivePagedListBuilder(
        transactionRepository.getAllTransactions(), 10
    ).build()


    init {

        // Listen to category sums
        launch {
            transactionRepository.getCategorizedSums().collect {
                chageState { copy(categorySums=it) }
            }
        }

        // Listen to months
        launch {
            transactionRepository.getMonths().collect {
                chageState { copy(months= it, selectedMonth = it.lastOrNull() ?: "") }
            }
        }

    }

    private fun chageState(block:HomeState.()->HomeState){
        val instantState = state.value ?: HomeState()
        _state.postValue(block(instantState))
    }

}