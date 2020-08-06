package com.paydaybank.android.core.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.insetter.Insetter
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
open class BaseActivity(): AppCompatActivity() {

    private val errorHandler = CoroutineExceptionHandler { _, throwable -> Timber.e(throwable) }

    fun launch(block:suspend CoroutineScope.()->Unit){
        lifecycleScope.launch(Dispatchers.IO + errorHandler, block = block)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // System bars
        Insetter.setEdgeToEdgeSystemUiFlags(window.decorView, true)
    }

}