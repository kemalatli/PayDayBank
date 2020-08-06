package com.paydaybank.data.repository.user.datasources

import com.paydaybank.data.core.BaseScope
import com.paydaybank.data.model.Customer
import com.paydaybank.data.offline.AppDatabase
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserLocalDataSource @Inject constructor(
    private val appDatabase: AppDatabase
): BaseScope() {

    suspend fun getCustomer():Customer?{
        return appDatabase.customerDao().getCustomer()
    }

    fun persistCustomer(customer: Customer) {
        launch {
            appDatabase.customerDao().updateCustomer(customer)
        }
    }

}