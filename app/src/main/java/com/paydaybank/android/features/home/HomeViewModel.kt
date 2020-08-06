package com.paydaybank.android.features.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.paydaybank.android.core.base.BaseViewModel
import com.paydaybank.data.repository.user.UserRepository
import com.paydaybank.data.repository.user.model.UserState
import kotlinx.coroutines.flow.collect


class HomeViewModel  @ViewModelInject constructor(
    private val userRepository: UserRepository
): BaseViewModel() {

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



}