package com.paydaybank.data.repository.transaction.datasources

import com.paydaybank.data.service.PayDayService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransactionRemoteDataSource @Inject constructor(
    private val payDayService: PayDayService
) {

    suspend fun getAccounts(id: Int) = payDayService.getAccounts(id)

    suspend fun getTransactions(list:List<Int>) = payDayService.getTransactions(list)


}