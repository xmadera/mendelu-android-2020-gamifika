package com.gamification.marketguards.data.base

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.gamification.marketguards.data.network.communication.service.SessionManager
import com.gamification.marketguards.data.network.communication.service.SessionManagerInterface

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
            return SessionManager(
                appContext
            ).fetchAuthToken() != null
        }

        fun getSessionManager(): SessionManagerInterface =
            SessionManager(
                appContext
            )
    }
}