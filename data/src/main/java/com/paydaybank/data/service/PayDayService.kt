package com.paydaybank.data.service

import com.paydaybank.data.model.AccountEntity
import com.paydaybank.data.model.CustomerEntity
import com.paydaybank.data.model.TransactionEntity
import com.paydaybank.data.repository.user.model.InputSignIn
import retrofit2.http.*

interface PayDayService {

    @POST("authenticate")
    suspend fun authenticate(@Body input: InputSignIn):CustomerEntity?

    @GET("customers/{id}")
    suspend fun getCustomer(@Path("id") id: Int):CustomerEntity?

    @GET("accounts")
    suspend fun getAccounts(@Query("customer_id") id: Int):List<AccountEntity>

    @GET("transactions")
    suspend fun getTransactions(@Query("account_id") list: List<Int>):List<TransactionEntity>

}