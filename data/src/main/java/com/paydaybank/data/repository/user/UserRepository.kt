package com.paydaybank.data.repository.user

import com.paydaybank.data.repository.user.model.UserState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface UserRepository {

    fun getUserState(): StateFlow<UserState>

}