package com.abizer_r.friendsconnect.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FriendsConnectApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@FriendsConnectApp)
            modules(appModule)
        }
    }
}