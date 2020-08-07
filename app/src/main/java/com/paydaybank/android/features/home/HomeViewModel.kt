package com.paydaybank.android.features.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import com.paydaybank.android.core.base.BaseViewModel
import com.paydaybank.data.repository.transaction.TransactionRepository
import com.paydaybank.data.repository.user.UserRepository
import kotlinx.coroutines.flow.collect
import timber.log.Timber


class HomeViewModel  @ViewModelInject constructor(
    private val userRepository: UserRepository,
    private val transactionRepository: TransactionRepository
): BaseViewModel() {

    // Home state
    private val _state:MutableLiveData<HomeState> = MutableLiveData()
    val state: LiveData<HomeState> = _state


    val paginatedTransactions = Transformations.switchMap(state){
        LivePagedListBuilder(
            transactionRepository.getTransactionsByMonth(it.selectedMonth), 10
        ).build()
    }


    init {

        // Listen to category sums
        launch {
            transactionRepository.getCategorizedSums().collect {
                val months = it.map { it.month }.distinct().sorted()
                chageState { copy(categorySums=it, months = months, selectedMonth = months.lastOrNull() ?: "") }
            }
        }

    }

    fun changeMonth(month:String) =  chageState { copy(selectedMonth = month) }

    private fun chageState(block:HomeState.()->HomeState){
        val instantState = state.value ?: HomeState()
        _state.postValue(block(instantState))
    }

}