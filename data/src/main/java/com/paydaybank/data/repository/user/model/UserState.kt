package com.paydaybank.data.repository.user.model

import com.paydaybank.data.model.CustomerEntity

sealed class UserState {

    object Loading: UserState()
    object Unauthenticated: UserState()
    class FailedSignIn(val message:String): UserState()
    class Authenticated(val customer: CustomerEntity): UserState()

}