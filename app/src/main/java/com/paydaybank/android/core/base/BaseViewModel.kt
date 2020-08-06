package com.paydaybank.android.core.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

open class BaseViewModel:ViewModel() {

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable)
        errorReceived(throwable)
    }

    // Warning messages to be displayed on UI
    private val _warningMessage = MutableLiveData<String>()
    val warningMessage: LiveData<String> get() = _warningMessage

    fun launch(block:suspend CoroutineScope.()->Unit){
        viewModelScope.launch(Dispatchers.IO + errorHandler, block = block)
    }

    fun warn(message:String?){
        _warningMessage.postValue(message)
    }

    open fun errorReceived(error:Throwable){

    }

}