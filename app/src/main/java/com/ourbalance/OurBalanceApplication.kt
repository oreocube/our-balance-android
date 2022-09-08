package com.ourbalance

import android.app.Application
import com.ourbalance.debug.OurBalanceDebugTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class OurBalanceApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(OurBalanceDebugTree())
        }
    }
}
