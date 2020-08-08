package com.paydaybank.android

import android.app.Application
import com.paydaybank.data.init.AppInitializer
import com.paydaybank.data.init.AppInitializers
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class App: Application() {

    @Inject lateinit var appInitializers: AppInitializers

    override fun onCreate() {
        super.onCreate()
        // Initialize SDKs and eager setup modules
        appInitializers.init()
    }

}