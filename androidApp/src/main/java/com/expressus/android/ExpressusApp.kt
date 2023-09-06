package com.expressus.android

import android.app.Application
import android.content.Context
import com.expressus.domain.DependencyInjection
import com.expressus.sharedPrefsForPlatformDependencies
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class ExpressusApp : Application() {

    override fun onCreate() {
        super.onCreate()
        sharedPrefsForPlatformDependencies = applicationContext.getSharedPreferences(applicationContext.packageName, Context.MODE_PRIVATE)
        DependencyInjection.initKoin {
            androidLogger()
            androidContext(this@ExpressusApp)
        }
    }
}