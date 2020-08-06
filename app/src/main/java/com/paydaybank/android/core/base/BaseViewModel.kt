package com.paydaybank.android.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

open class BaseViewModel:ViewModel() {

    private val errorHandler = CoroutineExceptionHandler { _, throwable -> Timber.e(throwable) }

    fun launch(block:suspend CoroutineScope.()->Unit){
        viewModelScope.launch(Dispatchers.IO + errorHandler, block = block)
    }

}