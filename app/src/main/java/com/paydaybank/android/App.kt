package com.paydaybank.android

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App: Application() {

    override fun onCreate() {
        super.onCreate()

        // TODO move to initializer
        Timber.plant(Timber.DebugTree())

    }

}