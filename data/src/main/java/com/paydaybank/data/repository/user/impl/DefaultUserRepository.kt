package com.paydaybank.data.repository.user.impl

import com.paydaybank.data.repository.user.UserRepository
import com.paydaybank.data.repository.user.model.UserState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DefaultUserRepository:UserRepository {

    private val userState :MutableStateFlow<UserState> = MutableStateFlow(UserState.Loading)

    init {
        // Fetch user state
        fetchUserState()
    }

    private fun fetchUserState() {
        userState.value = UserState.Unauthenticated
    }

    override fun getUserState(): StateFlow<UserState> = userState


}