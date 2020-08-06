package com.paydaybank.data.repository.user.impl

import com.paydaybank.data.core.BaseScope
import com.paydaybank.data.repository.user.UserRepository
import com.paydaybank.data.repository.user.datasources.UserLocalDataSource
import com.paydaybank.data.repository.user.datasources.UserRemoteDataSource
import com.paydaybank.data.repository.user.model.InputSignIn
import com.paydaybank.data.repository.user.model.UserState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultUserRepository @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource,
    private val userRemoteDataSource: UserRemoteDataSource
):BaseScope(), UserRepository {

    private val userState :MutableStateFlow<UserState> = MutableStateFlow(UserState.Loading)

    init {
        // Fetch user state
        fetchUserState()
    }

    private fun fetchUserState() {
        launch {
            val customer = userLocalDataSource.getCustomer()
            userState.value =if(customer==null){
                UserState.Unauthenticated
            }else{
                refreshUser(customer.id)
                UserState.Authenticated(customer)
            }
        }
    }

    private fun refreshUser(id:Int){
        launch {
            val customer = userRemoteDataSource.fetchClient(id)
            if (customer != null) {
                userLocalDataSource.persistCustomer(customer)
            }
        }
    }

    override fun getUserState(): StateFlow<UserState> = userState


    override suspend fun signIn(input: InputSignIn){
        try{

            // Set user state to loading
            userState.value = UserState.Loading

            // Get remote authentication
            val customer = userRemoteDataSource.authenticateCustomer(input)

            // Set authentication state
            if(customer==null){
                userState.value = UserState.FailedSignIn("Sign in failed")
            }else{
                userLocalDataSource.persistCustomer(customer)
                userState.value = UserState.Authenticated(customer)
            }

        }catch (ex:Exception){

            // Reset user state
            userState.value = UserState.Unauthenticated

            // Throw exception back to receiver
            throw ex
        }
    }

}