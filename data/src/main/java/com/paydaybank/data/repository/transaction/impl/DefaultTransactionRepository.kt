package com.paydaybank.data.repository.transaction.impl

import androidx.paging.DataSource
import com.paydaybank.data.core.BaseScope
import com.paydaybank.data.model.TransactionEntity
import com.paydaybank.data.repository.transaction.TransactionRepository
import com.paydaybank.data.repository.transaction.datasources.TransactionLocalDataSource
import com.paydaybank.data.repository.transaction.datasources.TransactionRemoteDataSource
import com.paydaybank.data.repository.transaction.model.CategorizedSum
import com.paydaybank.data.repository.user.UserRepository
import com.paydaybank.data.repository.user.model.UserState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultTransactionRepository @Inject constructor(
    private val userRepository: UserRepository,
    private val transactionLocalDataSource: TransactionLocalDataSource,
    private val transactionRemoteDataSource: TransactionRemoteDataSource
):BaseScope(), TransactionRepository {

    init {
        launch {
            userRepository.getUserState().collect {
                if(it is UserState.Authenticated) refreshTransactions(it.customer.id)
            }
        }
    }

    private fun refreshTransactions(customerId:Int) {
        launch {
            // Get accounts
            val acccounts = transactionRemoteDataSource.getAccounts(customerId)
            // Persist accounts
            transactionLocalDataSource.persistAccounts(acccounts)
            // Get transactions
            val transactions = transactionRemoteDataSource.getTransactions(acccounts.map { it.id })
            // Persist transactions
            transactionLocalDataSource.persistTransactions(transactions)
        }
    }


    override fun getAllTransactions(): DataSource.Factory<Int, TransactionEntity> = transactionLocalDataSource.getPaginatedTransactions(null)

    override fun getTransactionsByMonth(month: String): DataSource.Factory<Int, TransactionEntity> = transactionLocalDataSource.getPaginatedTransactions(month)

    override suspend fun getMonths(): Flow<List<String>> = transactionLocalDataSource.getMonths()

    override suspend fun getCategorizedSums(): Flow<List<CategorizedSum>> = transactionLocalDataSource.getCategorySums()


}