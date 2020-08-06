package com.paydaybank.android.features.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.paging.LivePagedListBuilder
import com.paydaybank.android.core.base.BaseViewModel
import com.paydaybank.data.repository.transaction.TransactionRepository
import com.paydaybank.data.repository.user.UserRepository


class HomeViewModel  @ViewModelInject constructor(
    private val userRepository: UserRepository,
    private val transactionRepository: TransactionRepository
): BaseViewModel() {


    val paginatedTransactions = LivePagedListBuilder(
        transactionRepository.getAllTransactions(), 10
    ).build()


    init {

        //val factory = transactionRepository.getAllTransactions()

    }

}