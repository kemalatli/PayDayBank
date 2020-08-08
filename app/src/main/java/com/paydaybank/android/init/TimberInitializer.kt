package com.paydaybank.android.init

import com.paydaybank.android.BuildConfig
import com.paydaybank.data.init.AppInitializer
import timber.log.Timber
import javax.inject.Inject

class TimberInitializer @Inject constructor():AppInitializer {

    override fun initialize() {
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }else{
            //TODO Implement a remote logging framework here to catch WARN and ERROR states of the app in production
            Timber.plant(Timber.DebugTree())
        }
    }

}