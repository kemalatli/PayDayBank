package com.paydaybank.android.features.auth

import android.text.Editable
import android.util.Patterns
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.paydaybank.android.core.base.BaseViewModel
import com.paydaybank.data.repository.user.UserRepository
import com.paydaybank.data.repository.user.model.InputSignIn
import com.paydaybank.data.repository.user.model.UserState
import kotlinx.coroutines.flow.collect
import retrofit2.HttpException


class AuthViewModel  @ViewModelInject constructor(
    private val userRepository: UserRepository
):BaseViewModel() {

    // User state for initial navigation logic
    private val _userState = MutableLiveData<UserState>()
    val userState: LiveData<UserState> get() = _userState

    init {
        // Collect user state from user repository
        launch {
            userRepository.getUserState().collect {
                _userState.postValue(it)
            }
        }
    }

    fun signIn(mail:Editable?, password:Editable?){
        // Validate mail
        if(mail.isNullOrEmpty()) {
            warn("Please input your email address")
            return
        }
        if(Patterns.EMAIL_ADDRESS.matcher(mail.toString()).matches().not()){
            warn("Please input a valid email address")
            return
        }
        // Validate password
        if(password.isNullOrEmpty()) {
            warn("Please input your password")
            return
        }
        // Submit login request
        val input = InputSignIn(
            mail.toString(),
            password.toString()
        )
        // Submit
        launch {
            userRepository.signIn(input)
        }

    }

    override fun errorReceived(error: Throwable) {
        if(error is HttpException){
            when(error.code()){
                404 -> warn("Looks like you don't have an account. Why don't you create one?")
            }
        }
    }

}