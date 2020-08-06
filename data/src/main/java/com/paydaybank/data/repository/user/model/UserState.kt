package com.paydaybank.data.repository.user.model

import com.paydaybank.data.model.Customer

sealed class UserState {

    object Loading: UserState()
    object Unauthenticated: UserState()
    class Authenticated(val customer: Customer): UserState()

}