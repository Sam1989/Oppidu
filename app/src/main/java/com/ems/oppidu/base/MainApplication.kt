package com.ems.oppidu.base

import android.app.Application
import android.content.Context
import com.google.firebase.crashlytics.FirebaseCrashlytics

//import com.google.firebase.crashlytics.FirebaseCrashlytics


class MainApplication : Application() {

    companion object {
        private lateinit var instance: MainApplication
        fun get(): MainApplication = instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
//        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)

    }

    fun getContext(): Context {
        return applicationContext
    }
}