package com.gamification.marketguards.data.base

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.se.omapi.Session
import com.gamification.marketguards.R
import com.gamification.marketguards.data.network.SessionManager
import com.gamification.marketguards.data.network.SessionManagerInterface

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext!!
    }

    companion object {

        /**
         * Returns app context
         * @return
         */
        @SuppressLint("StaticFieldLeak")
        lateinit var appContext: Context
            private set

        fun isLoggedIn(): Boolean {
            return SessionManager(appContext).fetchAuthToken() != null
        }

        fun getSessionManager(): SessionManagerInterface = SessionManager(appContext)
    }
}