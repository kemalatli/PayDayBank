package com.paydaybank.android.features.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import com.paydaybank.android.core.base.BaseViewModel
import com.paydaybank.data.repository.transaction.TransactionRepository
import com.paydaybank.data.repository.user.UserRepository
import com.paydaybank.data.repository.user.model.UserState
import kotlinx.coroutines.flow.collect


class HomeViewModel  @ViewModelInject constructor(
    private val userRepository: UserRepository,
    private val transactionRepository: TransactionRepository
): BaseViewModel() {

    // Home state
    private val _state:MutableLiveData<HomeState> = MutableLiveData()
    val state: LiveData<HomeState> = _state


    // User state
    private val _userState = MutableLiveData<UserState>()
    val userState: LiveData<UserState> get() = _userState


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

        // Collect user state from user repository
        launch {
            userRepository.getUserState().collect {
                _userState.postValue(it)
            }
        }

    }

    fun changeMonth(month:String) =  chageState { copy(selectedMonth = month) }

    fun logout() = userRepository.logout()

    private fun chageState(block:HomeState.()->HomeState){
        val instantState = state.value ?: HomeState()
        _state.postValue(block(instantState))
    }



}