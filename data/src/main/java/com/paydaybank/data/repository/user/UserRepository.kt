package com.paydaybank.data.repository.user

import com.paydaybank.data.model.CustomerEntity
import com.paydaybank.data.repository.user.model.InputSignIn
import com.paydaybank.data.repository.user.model.UserState
import kotlinx.coroutines.flow.StateFlow

interface UserRepository {

    fun getUserState(): StateFlow<UserState>

    fun logout()

    suspend fun signIn(input:InputSignIn)

    suspend fun createAccount(newCustomer: CustomerEntity)

}