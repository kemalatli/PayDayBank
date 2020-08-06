package com.paydaybank.data.repository.transaction

import androidx.paging.DataSource
import com.paydaybank.data.model.TransactionEntity
import com.paydaybank.data.repository.transaction.model.CategorizedSum
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {

    fun getAllTransactions():DataSource.Factory<Int, TransactionEntity>

    fun getTransactionsByMonth(month:String):DataSource.Factory<Int, TransactionEntity>

    suspend fun getMonths(): Flow<List<String>>

    suspend fun getCategorizedSums(): Flow<List<CategorizedSum>>

}