package com.paydaybank.data.repository.user.datasources

import com.paydaybank.data.repository.user.model.InputSignIn
import com.paydaybank.data.service.PayDayService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRemoteDataSource @Inject constructor(
    private val payDayService: PayDayService
) {

    suspend fun authenticateCustomer(inputSignIn: InputSignIn) = payDayService.authenticate(inputSignIn)

    suspend fun fetchClient(id: Int) = payDayService.getCustomer(id)

}