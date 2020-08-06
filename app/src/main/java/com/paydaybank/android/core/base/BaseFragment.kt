package com.paydaybank.android.core.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class BaseFragment(@LayoutRes contentLayoutRes:Int):Fragment(contentLayoutRes) {

    abstract val binding:ViewBinding

    private val errorHandler = CoroutineExceptionHandler { _, throwable -> Timber.e(throwable) }

    fun launch(block:suspend CoroutineScope.()->Unit){
        lifecycleScope.launch(Dispatchers.IO + errorHandler, block = block)
    }

}