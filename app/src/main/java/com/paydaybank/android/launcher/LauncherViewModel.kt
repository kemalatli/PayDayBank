package com.paydaybank.android.launcher

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.paydaybank.android.core.base.BaseViewModel
import com.paydaybank.data.repository.user.UserRepository
import com.paydaybank.data.repository.user.model.UserState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn

class LauncherViewModel  @ViewModelInject constructor(
    private val userRepository: UserRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
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

}