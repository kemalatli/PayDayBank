package com.paydaybank.data.repository.transaction.datasources

import androidx.paging.DataSource
import com.paydaybank.data.core.BaseScope
import com.paydaybank.data.model.AccountEntity
import com.paydaybank.data.model.CustomerEntity
import com.paydaybank.data.model.TransactionEntity
import com.paydaybank.data.offline.AppDatabase
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransactionLocalDataSource @Inject constructor(
    private val appDatabase: AppDatabase
): BaseScope() {

    fun getPaginatedTransactions(month:String?): DataSource.Factory<Int, TransactionEntity> {
        return if(month.isNullOrEmpty()){
            appDatabase.transactionDao().getTransactions()
        }else{
            appDatabase.transactionDao().getTransactionsByMonth(month)
        }
    }

    fun getMonths() = appDatabase.transactionDao().getMonths()

    fun getCategorySums() = appDatabase.transactionDao().getCategorySums()

    fun persistAccounts(acccounts: List<AccountEntity>) {
        launch {
            appDatabase.accountDao().updateAccounts(acccounts)
        }
    }

    fun persistTransactions(transactions: List<TransactionEntity>) {
        launch {
            val sdf = SimpleDateFormat("yyyyMM", Locale.getDefault())
            val sdfTime = SimpleDateFormat("yyyyMMddHHmm", Locale.getDefault())
            val list = transactions.map { it.copy(month = sdf.format(it.date), time = sdfTime.format(it.date)) }
            appDatabase.transactionDao().updateTransactions(list)
        }
    }

}