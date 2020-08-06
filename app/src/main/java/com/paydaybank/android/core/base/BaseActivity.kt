package com.paydaybank.android.core.base

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import com.paydaybank.android.core.extensions.observe
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

    /**
     * Navigate to any activity in the app
     */
    fun navigateToActivity(clazz: Class<*>) {
        val intent = Intent(this, clazz)
        startActivity(intent)
        finish()
    }

    fun listenToWarningMessages(warning:LiveData<String>){
        observe(warning){
            Toast.makeText(this, it, Toast.LENGTH_SHORT).apply {
                setGravity(Gravity.TOP , 0, 0)
            }.show()
        }
    }

}