package com.paydaybank.data.repository.user.datasources

import com.paydaybank.data.core.BaseScope
import com.paydaybank.data.model.CustomerEntity
import com.paydaybank.data.offline.AppDatabase
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserLocalDataSource @Inject constructor(
    private val appDatabase: AppDatabase
): BaseScope() {

    suspend fun getCustomer():CustomerEntity?{
        return appDatabase.customerDao().getCustomer()
    }

    fun persistCustomer(customer: CustomerEntity) {
        launch {
            appDatabase.customerDao().updateCustomer(customer)
        }
    }

    suspend fun wipeUserData() {
        appDatabase.customerDao().deleteCustomer()
        appDatabase.accountDao().deleteAccounts()
        appDatabase.transactionDao().deleteTransactions()
    }

}