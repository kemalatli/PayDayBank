package com.paydaybank.data.core

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

open class BaseScope:CoroutineScope {

    private val errorHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Timber.e(throwable)
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + errorHandler

}